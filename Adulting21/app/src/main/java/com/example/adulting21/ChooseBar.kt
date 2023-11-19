/*Author: Savannah Crutchfield
* This page navigates you from choose bar page to bar info pages.
* Kutztown tavern does not have enough info for page so navigates to webpage */

package com.example.adulting21

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ChooseBar : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.choose_bar, container, false)

        //Take user to Shortys info page
        val shortysBar: ImageView = view.findViewById(R.id.shortysBar)
        shortysBar.setOnClickListener {
            val fragmentSwitch = ShortysInfo()
            replaceFragment(fragmentSwitch)
        }

        //Take user to KTown Pub info page
        val ktownPub: ImageView = view.findViewById(R.id.ktownPub)
        ktownPub.setOnClickListener {
            val fragmentSwitch = KtownPubInfo()
            replaceFragment(fragmentSwitch)
        }

        //Take user to Kutztown Taven Website
        val kutzTavern = view.findViewById<ImageView>(R.id.kutzTavern)
        kutzTavern.setOnClickListener{
            val profilePath = "https://www.kutztowntavern.com/"
            val packageName = "com.google.android"
            toAnotherAppOpen(requireContext(), profilePath, packageName)
        }

        return view
    }

    //Replace fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    //Have phone open other app, launching from Adulting21
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

}