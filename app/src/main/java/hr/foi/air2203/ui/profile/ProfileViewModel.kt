package hr.foi.air2203.ui.profile

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.userProfileChangeRequest
import hr.foi.air2203.repository.UserRepository
import hr.foi.air2203.utils.startEditEmailActivity
import hr.foi.air2203.utils.startEditPasswordActivity
import hr.foi.air2203.utils.startEditUsernameActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(
    private val repository: UserRepository
    ) : ViewModel() {

    var password: String? = null
    var username: String? = null
    var emailEdit: String? = null
    var editListener: EditListener? = null
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
        val user by lazy {
            repository.currentUser()
        }

        var name: String? = user?.displayName
        var email: String? = user?.email

        fun gotoeditpassword(view: View) {
            view.context.startEditPasswordActivity()
        }

        fun gotoeditusername(view: View) {
            view.context.startEditUsernameActivity()
        }

        fun gotoeditemail(view: View) {
            view.context.startEditEmailActivity()
        }


    fun editPassword() {
            if (password.isNullOrEmpty()) {
                editListener?.onFailure("Please input all values")
                return
            }
            if(!isValidPassword(password!!)) {
                return
            }
            editListener?.onStarted()
            val disposable = repository.updatePassword(password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    editListener?.onSuccess()
                }, {
                    editListener?.onFailure(it.message!!)
                })

            disposables.add(disposable)

        }

    fun editUsername() {
        if (username.isNullOrEmpty()) {
            editListener?.onFailure("Please input username.")
            return
        }
        val profileUpdates = userProfileChangeRequest {
            displayName = "$username"
        }
        editListener?.onStarted()
        val disposable = repository.updateprofile(profileUpdates)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                editListener?.onSuccess()
            }, {
                editListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)

    }

    fun editEmail() {
        if (emailEdit.isNullOrEmpty()) {
            editListener?.onFailure("Please input username.")
            return
        }

        if(!isValidEmail(emailEdit!!)) {
            return
        }
        val profileUpdates = userProfileChangeRequest {
            email = "$emailEdit"
        }
        editListener?.onStarted()
        val disposable = repository.updateprofile(profileUpdates)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                editListener?.onSuccess()
            }, {
                editListener?.onFailure(it.message!!)
            })

        disposables.add(disposable)

    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) {
            editListener?.onFailure("Password must be 8 characters or more.")
            return false}
        if (password.filter { it.isDigit() }.firstOrNull() == null){
            editListener?.onFailure("Password must have atleast one digit.")
            return false}
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {
            editListener?.onFailure("Password must have atleast one uppercase letter.")
            return false}
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) {
            editListener?.onFailure("Password must have atleast one lowercase letter.")
            return false}
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {
            editListener?.onFailure("Password must have atleast one special character.")
            return false}

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            true
        else{
            editListener?.onFailure("Email address is not valid.")
            false
        }


    }

    }