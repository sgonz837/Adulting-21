package com.example.adulting21

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val button9 = findViewById<Button>(R.id.login_btn)
        //if login button is clicked, then execute tasks
        button9.setOnClickListener {

            //code here if login button is clicked

            //val intent = Intent(this, Navigation::class.java)
            //startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}