/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */

package com.example.adulting21

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var imageViews: List<ImageView>
    // Declare a variable for the loading layout
    private lateinit var loadingLayout: FrameLayout

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // Initialize the loading layout
        loadingLayout = view.findViewById(R.id.loadingLayout)
        // Initially, set the loading layout as invisible
        loadingLayout.visibility = View.GONE

        val drinkButton = view.findViewById<Button>(R.id.drinkBtn)

        drawerLayout = view.findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            view.findViewById(R.id.toolbar),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = view.findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle home item click
                    Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_settings -> {
                    // Handle settings item click
                    Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    // Handle logout item click
                    Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }


        // Set click listener for the Button
        drinkButton.setOnClickListener {
            val bundle = Bundle()
           // bundle.putInt("drinkNum", +1) // You can put your desired value here
            bundle.putInt("drinkNum", bundle.getInt("drinkNum") + 1) // Increment the value by 1
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

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()

            val randomDrinkName1 = view.findViewById<TextView>(R.id.drinkName1)
            val randomDrinkName2 = view.findViewById<TextView>(R.id.drinkName2)
            val randomDrinkName3 = view.findViewById<TextView>(R.id.drinkName3)
            val randomDrinkName4 = view.findViewById<TextView>(R.id.drinkName4)
            val randomDrinkName5 = view.findViewById<TextView>(R.id.drinkName5)
            val randomDrinkName6 = view.findViewById<TextView>(R.id.drinkName6)


            withContext(Dispatchers.Main) {
                response.forEachIndexed { index, cocktail ->
                    when (index) {
                        0 -> {
                            randomDrinkName1.text = cocktail.name

                        }

                        1 -> {
                            randomDrinkName2.text = cocktail.name

                        }

                        2 -> {
                            randomDrinkName3.text = cocktail.name
                        }

                        3 -> {
                            randomDrinkName4.text = cocktail.name

                        }

                        4 -> {
                            randomDrinkName5.text = cocktail.name

                        }

                        5 -> {
                            randomDrinkName6.text = cocktail.name

                        }
                    }
                }
            }
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
            replaceFragment(fragment2)
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        val rumDrink = view.findViewById<ImageView>(R.id.rumBottleImage)
        rumDrink.setOnClickListener {
            val bundles3 = Bundle()
            bundles3.putInt("drinkBottleNum", 3) // You can put your desired value here

            val fragment3 = bottleDrinkInfo()
            fragment3.arguments = bundles3
            replaceFragment(fragment3)
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        val tequillaDrinkkk = view.findViewById<ImageView>(R.id.tequillaBottleImage)
        tequillaDrinkkk.setOnClickListener {
            val bundles4 = Bundle()
            bundles4.putInt("drinkBottleNum", 4) // You can put your desired value here

            val fragment4 = bottleDrinkInfo()
            fragment4.arguments = bundles4
            replaceFragment(fragment4)
            //val intent = Intent(this, login::class.java)
            //startActivity(intent)
        }

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        navigationView = view.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle home item click
                    Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }

                R.id.nav_settings -> {
                    // Handle settings item click
                    Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    drawerLayout.closeDrawers()
                    true
                }


                R.id.nav_logout -> {
                    // Handle logout item click
                    Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
        }
        // Check if cached data is available, and display it if present
        if (viewModel.cachedCocktails != null) {
            displayCocktails(viewModel.cachedCocktails!!)
        } else {
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

            // Inside the onCreateView method, before making the API call
            loadingLayout.visibility = View.VISIBLE
            // If cached data is not available, make the API call
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = CocktailApiService()
                val response = apiService.popularCocktails()
                withContext(Dispatchers.Main) {
                    viewModel.cachedCocktails = response
                    displayCocktails(response)

                    // Wait for 2 seconds (2000 milliseconds)
                    delay(1000)
                    // After the API call is complete, set loadingLayout visibility to GONE
                    loadingLayout.visibility = View.GONE
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


