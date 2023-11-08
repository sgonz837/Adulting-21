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

class register : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)

        firebaseAuth = FirebaseAuth.getInstance()

        val buttonLogin = findViewById<Button>(R.id.button6)
        //if login button is clicked, do tasks
        buttonLogin.setOnClickListener {
            Log.d("TAG","Succesful2")
            register_page(it)

            //add code code when login button is clicked.

            //this is code to switch to homepage
            //val intent = Intent(this, register::class.java)
            //startActivity(intent)
        }
    }

    fun register_page(view: View) {
        Log.d("TAG","Succesful")
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Navigation::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener(this) { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }
}