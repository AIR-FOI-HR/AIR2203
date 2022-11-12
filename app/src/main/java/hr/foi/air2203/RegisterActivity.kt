package hr.foi.air2203

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //TODO do impl
        db = Firebase.firestore

        val user = hashMapOf(
            "data" to "rosa@gmail.com"
        )

        db.collection("data")
            .add(user)
            .addOnSuccessListener { data ->
                Log.d(TAG, "Data added with ID: ${data.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding data", e)
            }

    }
}