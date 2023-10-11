/*
    Author: Sayan Gonzalez
    Description: At first all the api calls were in this file but I have implemented a bottom
                 Navigation Bar and it required me to use fragments so this file for now
                 Just handles the navigation bar and calls the appropriate layout file when user
                 presses on one of the navigation buttons

                 More can be added but for now, if you wanted to edit the homepage, profile, etc.
                 with code then you would have to go to the appropriate fragment file and do the
                 code there.
*/

package com.example.adulting21

import android.content.Intent
import com.example.adulting21.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

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
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        //setContentView(R.layout.homepage)
//        setContentView(R.layout.register_page)
//
//            val buttonLogin = findViewById<Button>(R.id.butoon)
//            //First Page so These Two go to either login or register
//            buttonLogin.setOnClickListener {
//                Log.d("TAG","Test")
//                val intent = Intent(this, register::class.java)
//               startActivity(intent)
//            }
//
////            val buttonRegister = findViewById<Button>(R.id.button6)
////            buttonRegister.setOnClickListener {
////                val intent = Intent(this, login::class.java)
////            startActivity(intent)
////            }
        }
    //}

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
                    val intent = Intent(this, login::class.java)
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


