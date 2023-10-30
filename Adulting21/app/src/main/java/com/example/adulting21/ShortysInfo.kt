/*
    Author: Savannah Crutchfield
    Description: Shorty's Bar info page.
*/

package com.example.adulting21

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
//import com.google.android.material.bottomnavigation.BottomNavigationView

class ShortysInfo : Fragment() {
    //private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.shortys_info, container, false)
        //Instagram
        val insta = view.findViewById<ImageView>(R.id.insta)
        insta.setOnClickListener {
            val profilePath = "https://instagram.com/shortysbar"
            val packageName = "com.instagram.android"
            toAnotherAppOpen(requireContext(), profilePath, packageName)
        }

        //facebook
        val fb = view.findViewById<ImageView>(R.id.fb)
        fb.setOnClickListener {
            val profilePath = "https://www.facebook.com/shortyskutztown"
            val packageName = "com.facebook.android"
            toAnotherAppOpen(requireContext(), profilePath, packageName)
        }

        //tiktok
        val tiktok = view.findViewById<ImageView>(R.id.tiktok)
        tiktok.setOnClickListener {
            val profilePath = "https://www.tiktok.com/@shortysbar"
            val packageName = "com.tiktok.android"
            toAnotherAppOpen(requireContext(), profilePath, packageName)
        }

        //twitter
        val twitter = view.findViewById<ImageView>(R.id.twitter)
        twitter.setOnClickListener {
            val profilePath = "https://x.com/ShortysKutztown"
            val packageName = "com.x.android"
            toAnotherAppOpen(requireContext(), profilePath, packageName)
        }

        return view
    }
}


    //Send linked app
  /*  private fun toAnotherAppOpen(profilePath: String, openAppPackageName: String) {
        val uri = Uri.parse(profilePath)
        try {
            startActivity(Intent(Intent.ACTION_VIEW, uri).setPackage(openAppPackageName))
        }catch (e:Exception){
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

    }*/
    private fun toAnotherAppOpen(context: Context, profilePath: String, openAppPackageName: String) {
        val uri = Uri.parse(profilePath)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            intent.setPackage(openAppPackageName)
            context.startActivity(intent)
        } catch (e: Exception) {
            // Log the exception message
            e.printStackTrace()

            // Provide a fallback option (e.g., open in browser)
            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(browserIntent)
        }
    }





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

