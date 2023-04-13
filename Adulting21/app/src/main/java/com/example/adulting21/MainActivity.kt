package com.example.adulting21


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        setContentView(R.layout.activity_main)
=======
        setContentView(R.layout.drink_info)
>>>>>>> Stashed changes
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