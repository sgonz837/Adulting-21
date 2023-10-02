/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */
/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What I've done is set up a layout format to hold whatever API calls that we will do
                 then display it using the layout page
*/

package com.example.adulting21

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
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

        // Set click listeners for the ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]
            imageView.setOnClickListener {
                replaceFragment(DrinkInfoFragment())
            }
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

/*
package com.example.adulting21

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.adulting21.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.lifecycle.ViewModelProvider


class HomeFragment : Fragment() {

    //private var cachedCocktails: List<Cocktail>? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var layouts: List<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        layouts = listOf(
            view.findViewById(R.id.layout1),
            view.findViewById(R.id.layout2),
            view.findViewById(R.id.layout3),
            view.findViewById(R.id.layout4),
            view.findViewById(R.id.layout5),
            view.findViewById(R.id.layout6),
            view.findViewById(R.id.layout7),
            view.findViewById(R.id.layout8),
            view.findViewById(R.id.layout9),
            view.findViewById(R.id.layout10)
        )

        // Set click listeners for the layout buttons
        for (index in 0 until layouts.size) {
            val layout = layouts[index]
            layout.setOnClickListener {
                replaceFragment(DrinkInfoFragment())
            }
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
        // Clear all layouts
        layouts.forEach { it.removeAllViews() }

        // Display cocktails using the received data
        cocktails.take(layouts.size).forEachIndexed { index, cocktail ->
            val imageView = ImageView(requireContext())
            Picasso.get()
                .load(cocktail.imageUrl)
                .resize(
                    1000,
                    1000
                ) // Set width to 200 pixels and height to maintain aspect ratio
                .into(imageView)
            layouts[index].addView(imageView)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}


 */