package com.example.adulting21

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

import androidx.fragment.app.Fragment

class Navigation : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.homepage)

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




    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}


