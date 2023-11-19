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
import android.net.Uri
import com.example.adulting21.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        //setContentView(R.layout.bar_info)
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already logged in
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            //Will automatically continue to navigation if user is logged in
            val intent = Intent(this, Navigation::class.java)
            startActivity(intent)
            finish() // Finish MainActivity to prevent going back to it on pressing back button
        }

        val buttonLogin = findViewById<Button>(R.id.login_btn)
        //if login button is clicked call loginpage function
        buttonLogin.setOnClickListener {
            Log.d("TAG", "Succesful2")
            login_page(it)
        }
        val buttonreg = findViewById<Button>(R.id.GoToReg)
        //if register button is clicked, do go to the register tab
        buttonreg.setOnClickListener {
            GoToReg(it)
        }
        val buttonGC = findViewById<Button>(R.id.GuestContinue)
        //if guest button is clicked
        //TODO(set up to continue to mocktails page only
        // Can Probably remove button as well)
        buttonGC.setOnClickListener {
            guestcont(it)
        }
    }
    fun login_page(view: View) {
        Log.d("TAG", "SuccesfulLogin")
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
            return
        }
        // Sign in with the provided email and password
        firebaseAuth.signInWithEmailAndPassword(email, password)



            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful, navigate to the main page
                    val intent = Intent(this, Navigation::class.java)
                    startActivity(intent)

                    //inputs email information into firebase realtime database
                    val user = firebaseAuth.currentUser
                    val userId = user?.uid

                    // Save user data to Firebase Realtime Database
                    val databaseReference = FirebaseDatabase.getInstance().getReference("users")

                    val userData = HashMap<String, Any>()
                    userData["email"] = email
                    //userData["additionalField"] = additionalValue // Add other user data here

                    // Store under a unique ID (user ID in this case)
                    userId?.let {
                        databaseReference.child(it).setValue(userData)
                    }

                    finish()

                } else {
                    // Login failed, display an error message
                    Toast.makeText(
                        this, "Login failed", Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun GoToReg(view: View) {
        val intent = Intent(this, register::class.java)
        startActivity(intent)
        finish()
    }

    fun guestcont(view: View) {
        val intent = Intent(this, Navigation::class.java)
        startActivity(intent)
        finish()

    }
    //handles android back arrow press. Keeps user on login page
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
