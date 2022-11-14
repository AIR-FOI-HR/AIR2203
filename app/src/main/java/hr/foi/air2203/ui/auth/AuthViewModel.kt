package hr.foi.air2203.ui.auth

import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.userProfileChangeRequest
import hr.foi.air2203.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var firstname: String? = null
    var lastname: String? = null

    var authListener: AuthListener? = null


    private val disposables = CompositeDisposable()

    val user by lazy {
        repository.currentUser()
    }


    fun login() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password.")
            return
        }

        authListener?.onStarted()


        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(repository.currentUser()!!.isEmailVerified) {
                    authListener?.onSuccess()
                }
                else{
                    repository.logout()
                    authListener?.onFailure("Your email address needs to be verified.")
                }
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }


    fun signup() {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Please input all values")
            return
        }
        if(!validate(email!!, password!!)) {
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val profileUpdates = userProfileChangeRequest {
                    displayName = "$firstname $lastname"
                }
                repository.updateprofile(profileUpdates)
                repository.sendverification()
                repository.logout()
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)

    }

    fun goToSignup(view: View) {
        Intent(view.context, RegisterActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) {
            authListener?.onFailure("Password must be 8 characters or more.")
            return false}
        if (password.filter { it.isDigit() }.firstOrNull() == null){
            authListener?.onFailure("Password must have atleast one digit.")
            return false}
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {
            authListener?.onFailure("Password must have atleast one uppercase letter.")
            return false}
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) {
            authListener?.onFailure("Password must have atleast one lowercase letter.")
            return false}
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {
            authListener?.onFailure("Password must have atleast one special character.")
            return false}

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            true
        else{
            authListener?.onFailure("Email address is not valid.")
            false
        }


    }

    fun validate(email: String, password: String): Boolean {

        if(isValidEmail(email!!) && isValidPassword(password!!)
        ) {
            return true
        }
        return false
    }
}