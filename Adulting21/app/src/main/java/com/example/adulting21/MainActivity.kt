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
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.homepage)
        setContentView(R.layout.login_register)

            val buttonLogin = findViewById<Button>(R.id.button_login)
            buttonLogin.setOnClickListener {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }

            val buttonRegister = findViewById<Button>(R.id.button_register)
            buttonRegister.setOnClickListener {
                val intent = Intent(this, register::class.java)
                startActivity(intent)
            }
        }

        fun buttonLogin(view: View) {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }}


/*
        //replaceFragment(HomeFragment())
        val fragment = supportFragmentManager.findFragmentByTag("HomeFragment")

        if (fragment == null) {
            // The fragment hasn't been added yet, so add it now
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, HomeFragment(), "HomeFragment")
                .commit()
        }
/*
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, DrinkInfoFragment())
        fragmentTransaction.commit()
*/
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        //bottomNavigationView.setOnItemSelectedListener { item ->
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
                R.id.search -> replaceFragment(SearchFragment())
            }
            true
        }


 */
/*
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}

 */