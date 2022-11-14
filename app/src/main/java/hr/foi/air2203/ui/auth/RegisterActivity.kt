package hr.foi.air2203.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import hr.foi.air2203.R
import hr.foi.air2203.databinding.ActivityRegisterBinding
import hr.foi.air2203.ui.home.MainPageActivity
import hr.foi.air2203.utils.startHomeActivity
import hr.foi.air2203.utils.startLoginActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class RegisterActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {

    }

    override fun onSuccess() {
        Intent(this, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}





//class RegisterActivity : AppCompatActivity() {
//    private lateinit var db: FirebaseFirestore
//    private lateinit var binding: ActivityRegisterBinding
//    private lateinit var auth: FirebaseAuth
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        var user: FirebaseUser
//
//        binding.btnRegister.setOnClickListener{
//            auth = Firebase.auth
//            if(validate()) {
//                auth.createUserWithEmailAndPassword(
//                    binding.editTestEmail.text.toString(),
//                    binding.editTestPassword.text.toString()
//                )
//                    .addOnCompleteListener(this) { task->
//                        if(task.isSuccessful){
//                            Log.d(TAG, "createUserWithEmail:success")
//                            Toast.makeText(
//                                baseContext, "Registration successful. A verification email has been sent.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            val profileUpdates = userProfileChangeRequest {
//                                displayName = binding.editTextFirstName.text.toString() + " " + binding.editTextLastName.text.toString()
//
//                            }
//                            user = auth.currentUser!!
//                            user!!.updateProfile(profileUpdates)
//                            user.sendEmailVerification()
//                                .addOnCompleteListener { task ->
//                                    if (task.isSuccessful) {
//                                        Log.d(TAG, "Email sent.")
//                                    }
//                                }
//                            auth.signOut()
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                        }
//                        else{
//                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                            Toast.makeText(
//                                baseContext, "Registration failed.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            //TODO
//                            //updateUI(null)
//                        }
//                    }
//            }
//        }
////        //TODO do impl
////        db = Firebase.firestore
////
////        val user = hashMapOf(
////            "data" to "rosa@gmail.com"
////        )
////
////        db.collection("data")
////            .add(user)
////            .addOnSuccessListener { data ->
////                Log.d(TAG, "Data added with ID: ${data.id}")
////            }
////            .addOnFailureListener { e ->
////                Log.w(TAG, "Error adding data", e)
////            }
//
//    }
//    private fun validate(): Boolean {
//
//            if(isValidEmail(binding.editTestEmail.text.toString()) && isValidPassword(binding.editTestPassword.text.toString())
//            ) {
//                return true
//            }
//                return false
//    }
//    private fun isValidPassword(password: String): Boolean {
//        if (password.length < 8) { Toast.makeText(
//            baseContext, "Password must be 8 or more characters.",
//            Toast.LENGTH_SHORT
//        ).show()
//            return false}
//        if (password.filter { it.isDigit() }.firstOrNull() == null){               Toast.makeText(
//            baseContext, "Password must have at least one digit.",
//            Toast.LENGTH_SHORT
//        ).show()
//            return false}
//        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {               Toast.makeText(
//            baseContext, "Password must have at least one uppercase letter.",
//            Toast.LENGTH_SHORT
//        ).show()
//            return false}
//        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) {               Toast.makeText(
//            baseContext, "Password must have at least one lowercase letter.",
//            Toast.LENGTH_SHORT
//        ).show()
//            return false}
//        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {               Toast.makeText(
//            baseContext, "Password must have one special character.",
//            Toast.LENGTH_SHORT
//        ).show()
//            return false}
//
//        return true
//    }
//
//    private fun isValidEmail(email: String): Boolean {
//        return if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
//            true
//        else{
//            Toast.makeText(
//                baseContext, "Email is not valid.",
//                Toast.LENGTH_SHORT
//            ).show()
//            false
//        }
//
//    }
//
//}