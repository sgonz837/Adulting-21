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
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.homepage)
        setContentView(R.layout.login_register)

        //setContentView(R.layout.bar_info)
        //Instagram
/*        val insta = findViewById<ImageView>(R.id.insta)
        insta.setOnClickListener {
            val profilePath = "https://instagram.com/shortysbar"
            val packageName = "come.instagram.android"
            toAnotherAppOpen(profilePath, packageName)
        }

        //facebook
        val fb = findViewById<ImageView>(R.id.fb)
        fb.setOnClickListener {
            val profilePath = "https://www.facebook.com/shortyskutztown"
            val packageName = "come.facebook.android"
            toAnotherAppOpen(profilePath, packageName)
        }

        //tiktok
        val tiktok = findViewById<ImageView>(R.id.tiktok)
        tiktok.setOnClickListener {
            val profilePath = "https://www.tiktok.com/@shortysbar"
            val packageName = "come.tiktok.android"
            toAnotherAppOpen(profilePath, packageName)
        }

        //twiiter
        val twitter = findViewById<ImageView>(R.id.twitter)
        twitter.setOnClickListener {
            val profilePath = "https://x.com/ShortysKutztown"
            val packageName = "come.x.android"
            toAnotherAppOpen(profilePath, packageName)
        }
*/


        //val insta
        //val twitter
        //val fb

            val buttonLogin = findViewById<Button>(R.id.button_login)
            buttonLogin.setOnClickListener {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }

            val buttonRegister = findViewById<Button>(R.id.button_register)
            buttonRegister.setOnClickListener {
                val intent = Intent(this, login::class.java)
                startActivity(intent)
            }


        }

/*
    //Send linked app
    private fun toAnotherAppOpen(profilePath: String, openAppPackageName: String) {
        val uri = Uri.parse(profilePath)
        try {
            startActivity(Intent(Intent.ACTION_VIEW, uri).setPackage(openAppPackageName))
        }catch (e:Exception){
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

    } */



    /* //Share an app
        private fun shareURL() {
            val packageName = packageName // here your package Name
            val tiktokURL = "https://play.google.com/store/apps/details?id=$packageName"
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, tiktokURL)
            startActivity(Intent.createChooser(sendIntent, "Share Via"))
        }
    */
        fun buttonLogin(view: View) {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }}
