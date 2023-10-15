/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */

package com.example.adulting21

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.adulting21.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.ViewModelProvider


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var imageViews: List<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val drinkButton = view.findViewById<Button>(R.id.drinkBtn)

        // Set click listener for the Button
        drinkButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("drinkNum", 1) // You can put your desired value here
            bundle.putString("manualTime", "02:30") // Replace "YourManualTime" with your desired time

        }


        imageViews = listOf(
            view.findViewById(R.id.drinkImage1),
            view.findViewById(R.id.drinkImage2),
            view.findViewById(R.id.drinkImage3),
            view.findViewById(R.id.drinkImage4),
            view.findViewById(R.id.drinkImage5),
            view.findViewById(R.id.drinkImage6),
            view.findViewById(R.id.drinkImage7),
            view.findViewById(R.id.drinkImage8),
            view.findViewById(R.id.drinkImage9),
            view.findViewById(R.id.drinkImage10)
        )
        val apiService = CocktailApiService()
        val response = apiService.popularCocktails()

        val textView1 = view.findViewById<TextView>(R.id.drinkName3)
        textView1.text = response.joinToString(separator = "\n") {
            "${it.ingredient2}"
        }

        // Set click listeners for the ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]
            imageView.setOnClickListener {
                replaceFragment(DrinkInfoFragment())
            }
        }


        // In order to keep the number of fragment pages low,
        // I will relate a number to a drink and in the bottleDinkInfo page
        // I will check the integer in drinkBottleNum and display the number that i get
        // by using a if statement and call the related api call

        // drinkBottleNum = 1 (Vodka)
        //                  2 (Gin)
        //                  3 (Rum)
        //                  4 (Tequilla)
        val vodkaDrink = view.findViewById<ImageView>(R.id.vodkaBottleImage)
        vodkaDrink.setOnClickListener {
            Log.d("TAG", "message")
            val bundles1 = Bundle()
            bundles1.putInt("drinkBottleNum", 1) // You can put your desired value here

            val fragment1 = bottleDrinkInfo()
            fragment1.arguments = bundles1
            replaceFragment(fragment1)
        }


        val ginDrink = view.findViewById<ImageView>(R.id.ginBottleImage)
        ginDrink.setOnClickListener {
            val bundles2 = Bundle()
            bundles2.putInt("drinkBottleNum", 2) // You can put your desired value here
            val fragment2 = bottleDrinkInfo()
            fragment2.arguments = bundles2
            replaceFragment(bottleDrinkInfo())
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        val rumDrink = view.findViewById<ImageView>(R.id.rumBottleImage)
        rumDrink.setOnClickListener {
            val bundles3 = Bundle()
            bundles3.putInt("drinkBottleNum", 3) // You can put your desired value here

            val fragment3 = bottleDrinkInfo()
            fragment3.arguments = bundles3
            replaceFragment(bottleDrinkInfo())
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        val tequillaDrinkkk = view.findViewById<ImageView>(R.id.tequillaBottleImage)
        tequillaDrinkkk.setOnClickListener {
            val bundles4 = Bundle()
            bundles4.putInt("drinkBottleNum", 4) // You can put your desired value here

            val fragment4 = bottleDrinkInfo()
            fragment4.arguments = bundles4
            replaceFragment(bottleDrinkInfo())
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Check if cached data is available, and display it if present
        if (viewModel.cachedCocktails != null) {
            displayCocktails(viewModel.cachedCocktails!!)
        } else {
            // If cached data is not available, make the API call
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = CocktailApiService()
                val response = apiService.popularCocktails()
                withContext(Dispatchers.Main) {
                    viewModel.cachedCocktails = response
                    displayCocktails(response)
                }
            }
        }

        return view
    }

    private fun displayCocktails(cocktails: List<Cocktail>) {
        // Display cocktails using the received data
        cocktails.take(imageViews.size).forEachIndexed { index, cocktail ->
            val imageView = imageViews[index]
            Picasso.get()
                .load(cocktail.imageUrl)
                .resize(
                    1000,
                    1000
                ) // Set width to 200 pixels and height to maintain aspect ratio
                .into(imageView)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}