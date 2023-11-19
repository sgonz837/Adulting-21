package com.example.adulting21

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class register : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        firebaseAuth = FirebaseAuth.getInstance()

        val buttonLogin = findViewById<Button>(R.id.button6)
        //if login button is clicked, do tasks
        buttonLogin.setOnClickListener {
            Log.d("TAG", "Succesful2")
            register_page(it)

            //add code code when login button is clicked.

            //this is code to switch to homepage
            //val intent = Intent(this, register::class.java)
            //startActivity(intent)
        }
    }

    fun register_page(view: View) {
        Log.d("TAG", "Succesful")
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        val ContactName = findViewById<EditText>(R.id.ContactName).text.toString()
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber).text.toString()

        //error check to see if email and password are entered
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
            return
        }

        //error check for valid phone number length
        if (phoneNumber.isNotEmpty() && phoneNumber.length != 10 && phoneNumber.length != 12) {
            Toast.makeText(this, "Invalid phone number.", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    //navigates to the main page
                    val intent = Intent(this, Navigation::class.java)
                    startActivity(intent)

                    //inputs email information into firebase realtime database
                    val user = firebaseAuth.currentUser
                    val userId = user?.uid

                    // Save user data to Firebase Realtime Database
                    val databaseReference = FirebaseDatabase.getInstance().getReference("users")

                    val userData = HashMap<String, Any>()
                    userData["email"] = email
                    userData["CotName"] = ContactName
                    userData["CotNumber"] = phoneNumber
                    //userData["additionalField"] = additionalValue // Add other user data here

                    // Store under a unique ID (user ID in this case)
                    userId?.let {
                        databaseReference.child(it).setValue(userData)
                    }
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG)
                        .show()
                }
            }
            ?.addOnFailureListener(this) { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}