

package com.example.adulting21

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DrinkInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drink_info)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeFragment::class.java)
        startActivity(intent)
        finish()
    }
}