package com.example.adulting21

import com.example.adulting21.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //ActivityMainBinding binding;
    //private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.homepage)
        replaceFragment(HomeFragment())
        val fragment = supportFragmentManager.findFragmentByTag("HomeFragment")

        if (fragment == null) {
            // The fragment hasn't been added yet, so add it now
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, HomeFragment(), "HomeFragment")
                .commit()
        }


        //bottomNavigationView = findViewById(R.id.bottomNavigationView)
        //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        //bottomNavigationView.setOnItemSelectedListener { item ->
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                //R.id.home -> replaceFragment(HomeFragment())
                //R.id.home -> setContentView(R.layout.homepage)
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
            }
            true
        }


        //setContentView(R.layout.activity_main)

        //val button3 = findViewById<Button>(R.id.button3)
        /*
        val button3 = findViewById<ImageButton>(R.id.imageView5)
        button3.setOnClickListener {
            val intent = Intent(this, DrinkInfoActivity::class.java)
            startActivity(intent)
        }


         */

        //val button3: Button = findViewById(R.id.button3)
       //setContentView(R.layout.drink_info)

/*

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()
            withContext(Dispatchers.Main) {

                val layout = findViewById<LinearLayout>(R.id.layout)
                layout.removeAllViews()
                layout.orientation = LinearLayout.HORIZONTAL
                // Convert response to a string and set it to the TextView

                val textView = findViewById<TextView>(R.id.text_view)
                textView.text = response.joinToString(separator = "\n") {
             //       "${it.name} - ${it.imageUrl}"
                    "${it.name}"
                }

//\n
/*
                response.forEach { cocktail ->
                    val imageView = ImageView(this@MainActivity)
                    Picasso.get()
                        .load(cocktail.imageUrl)
                        .into(imageView)
                    layout.addView(imageView)
                }

 */

                response.forEachIndexed { index, cocktail ->
                    if (index == 0) {
                        val imageView = ImageView(this@MainActivity)
                        Picasso.get().load(cocktail.imageUrl).into(imageView)
                        layout.addView(imageView)
                        return@forEachIndexed
                    }
                }



/*
                response.forEach { cocktail ->
                    val imageView = ImageView(this@MainActivity)
                    Picasso.get()
                        .load(cocktail.imageUrl)
                        .resize(200, 0) // Set width to 200 pixels and height to maintain aspect ratio
                        .into(imageView)
                    layout.addView(imageView)
                }

 */


 */
           // }
        }
    //}
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}