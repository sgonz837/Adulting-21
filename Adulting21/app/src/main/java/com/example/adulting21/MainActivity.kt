package com.example.adulting21

import com.example.adulting21.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.view.View
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val button3 = findViewById<Button>(R.id.button3)
        val button3 = findViewById<ImageButton>(R.id.imageView5)
        button3.setOnClickListener {
            val intent = Intent(this, DrinkInfoActivity::class.java)
            startActivity(intent)
        }
        //val button3: Button = findViewById(R.id.button3)
       //setContentView(R.layout.drink_info)
/*
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = CocktailApiService()
                val response = apiService.getCocktails()

                withContext(Dispatchers.Main) {
                    // Handle response here
                    val textView = findViewById<TextView>(R.id.text_view)
                    textView.text = response
                }
            }

 */
        }

    }