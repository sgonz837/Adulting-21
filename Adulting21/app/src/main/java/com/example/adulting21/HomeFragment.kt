/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */

package com.example.adulting21


import android.content.Intent
import android.net.Uri
import MeterBac
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
import com.example.adulting21.settings.SettingsHub
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
    private lateinit var imageViews1: List<ImageView>
    // Declare a variable for the loading layout
    private lateinit var loadingLayout: FrameLayout

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private var drinkNum: Int = 1
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

        val sideNavFragment = SideNavigationDrawerFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.container_home, sideNavFragment)
        transaction.commit()

//        val drinkButton = view.findViewById<Button>(R.id.drinkBtn)

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

/*
        // Set click listener for the Button
        drinkButton.setOnClickListener {
            drinkNum++
            val bundle = Bundle()
           bundle.putInt("drinkNum", drinkNum) // You can put your desired value here
            val fragment = SettingsFragment()
            fragment.arguments = bundle
           // replaceFragment(fragment)

            //bundle.putInt("drinkNum", bundle.getInt("drinkNum", 0) + 1) // Increment the value by 1 and initialize to 0 if it doesn't exist
            //drinkNum = bundle.getInt("drinkNum") // Update the drinkNum variable
            //bundle.putInt("drinkNum", bundle.getInt("drinkNum") + 1) // Increment the value by 1
            Log.d("TAG", "drinkNum: $drinkNum") // Log the value of drinkNum


            Log.d("TAG","button clicked" )


        }

 */

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

        imageViews1 = listOf(
            view.findViewById(R.id.drinkImage11),
            view.findViewById(R.id.drinkImage22),
            view.findViewById(R.id.drinkImage33),
            view.findViewById(R.id.drinkImage44),
            view.findViewById(R.id.drinkImage55),
            view.findViewById(R.id.drinkImage66),
            view.findViewById(R.id.drinkImage77),
            view.findViewById(R.id.drinkImage88),
            view.findViewById(R.id.drinkImage99),
            view.findViewById(R.id.drinkImage100)
        )

        // Declare the cocktails list outside of GlobalScope.launch
        var cocktails: List<Cocktail>? = null
        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()
           // val cocktails = apiService.popularDrinks()
            cocktails = apiService.popularDrinks()
            //cocktails = apiService.popularCocktails()
            val popularResponse = apiService.popularDrinks()


            val randomDrinkName1 = view.findViewById<TextView>(R.id.drinkName1)
            val randomDrinkName2 = view.findViewById<TextView>(R.id.drinkName2)
            val randomDrinkName3 = view.findViewById<TextView>(R.id.drinkName3)
            val randomDrinkName4 = view.findViewById<TextView>(R.id.drinkName4)
            val randomDrinkName5 = view.findViewById<TextView>(R.id.drinkName5)
            val randomDrinkName6 = view.findViewById<TextView>(R.id.drinkName6)
            val randomDrinkName7 = view.findViewById<TextView>(R.id.drinkName7)
            val randomDrinkName8 = view.findViewById<TextView>(R.id.drinkName8)
            val randomDrinkName9 = view.findViewById<TextView>(R.id.drinkName9)
            val randomDrinkName10 = view.findViewById<TextView>(R.id.drinkName10)

            val randomDrinkName11 = view.findViewById<TextView>(R.id.drinkName11)
            val randomDrinkName22 = view.findViewById<TextView>(R.id.drinkName22)
            val randomDrinkName33 = view.findViewById<TextView>(R.id.drinkName33)
            val randomDrinkName44 = view.findViewById<TextView>(R.id.drinkName44)
            val randomDrinkName55 = view.findViewById<TextView>(R.id.drinkName55)
            val randomDrinkName66 = view.findViewById<TextView>(R.id.drinkName66)
            val randomDrinkName77 = view.findViewById<TextView>(R.id.drinkName77)
            val randomDrinkName88 = view.findViewById<TextView>(R.id.drinkName88)
            val randomDrinkName99 = view.findViewById<TextView>(R.id.drinkName99)
            val randomDrinkName100 = view.findViewById<TextView>(R.id.drinkName100)

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

                        6 -> {
                            randomDrinkName7.text = cocktail.name
                        }

                        7 -> {
                            randomDrinkName8.text = cocktail.name

                        }

                        8 -> {
                            randomDrinkName9.text = cocktail.name

                        }

                        9 -> {
                            randomDrinkName10.text = cocktail.name

                        }
                    }
                }




                popularResponse.forEachIndexed { index, cocktail ->
                    when (index) {
                        0 -> {
                            randomDrinkName11.text = cocktail.name

                        }

                        1 -> {
                            randomDrinkName22.text = cocktail.name

                        }

                        2 -> {
                            randomDrinkName33.text = cocktail.name
                        }

                        3 -> {
                            randomDrinkName44.text = cocktail.name

                        }

                        4 -> {
                            randomDrinkName55.text = cocktail.name

                        }

                        5 -> {
                            randomDrinkName66.text = cocktail.name

                        }

                        6 -> {
                            randomDrinkName77.text = cocktail.name
                        }

                        7 -> {
                            randomDrinkName88.text = cocktail.name

                        }

                        8 -> {
                            randomDrinkName99.text = cocktail.name

                        }

                        9 -> {
                            randomDrinkName100.text = cocktail.name

                        }
                    }
                }
            }
        }


// Inside your loop where you set click listeners for ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]

            imageView.setOnClickListener {
                if (index < cocktails?.size ?: 0) {
                    val cocktailId = cocktails?.get(index)?.id

                    val bundle = Bundle()
                    bundle.putString("cocktailId", cocktailId)

                    val drinkInfoFragment = DrinkInfoFragment()
                    drinkInfoFragment.arguments = bundle

                    replaceFragment(drinkInfoFragment)
                }
            }
        }

/*
        // Inside your loop where you set click listeners for ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]

            imageView.setOnClickListener {
                if (index < cocktail.size) {
                    val cocktailId = cocktail[index].id

                    val bundle = Bundle()
                    bundle.putString("cocktailId", cocktailId)

                    val drinkInfoFragment = DrinkInfoFragment()
                    drinkInfoFragment.arguments = bundle

                    replaceFragment(drinkInfoFragment)
                }
            }
        }

 */

/*
        // Inside the loop where you set click listeners for ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]
            val cocktailId = "your_cocktail_id_here" // Replace with the actual cocktail ID

            imageView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("cocktailId", cocktailId)

                val drinkInfoFragment = DrinkInfoFragment()
                drinkInfoFragment.arguments = bundle

                replaceFragment(drinkInfoFragment)
            }
        }

 */

/*
        // Set click listeners for the ImageViews
        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]
            imageView.setOnClickListener {
                replaceFragment(DrinkInfoFragment())
            }
        }

 */


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

                R.id.nav_car_pool -> {
                    openUber()
                    // Handle home item click
                    Toast.makeText(requireContext(), "Car pool clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }

                R.id.nav_mocktails -> {
                    val fragmentSwitch = MocktailFragment()
                    replaceFragment(fragmentSwitch)
                    // Handle home item click
                    Toast.makeText(requireContext(), "Mocktails clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }
                R.id.nav_settings -> {
                    val fragmentSwitch = SettingsHub()
                    replaceFragment(fragmentSwitch)
                    // Handle settings item click
                    Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    drawerLayout.closeDrawers()
                    true
                }
/*
                R.id.nav_bars_dropdown_trigger -> {
                    // Handle the "Bars" item click by showing the dropdown menu
                    val menuItem = navigationView.menu.findItem(R.id.nav_bars)
                    menuItem.actionView?.let { showBarsDropDownMenu(it) }
                    true
                }

 */


                R.id.nav_bars_dropdown_trigger -> {
                    //val fragmentSwitch = ChooseBar()
                    val fragmentSwitch = MeterBac()
                    replaceFragment(fragmentSwitch)
                    // Handle the "Bars" item click by showing the dropdown menu
                    //showBarsDropDownMenu(view)
                    true
                }



/*
                R.id.nav_bars -> {
                    // Handle logout item click
                    Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()

                    menuItem.actionView?.let { showBarsDropDownMenu(it) }
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

 */


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
            displayCocktails1(viewModel.cachedCocktails1!!)
        } else {
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

            // Inside the onCreateView method, before making the API call
            loadingLayout.visibility = View.VISIBLE
            // If cached data is not available, make the API call
            GlobalScope.launch(Dispatchers.IO) {
                val apiService = CocktailApiService()
                val response = apiService.popularCocktails()
                val response1 = apiService.popularDrinks()
                withContext(Dispatchers.Main) {
                    viewModel.cachedCocktails = response
                    //viewModel.cachedCocktails1 = response
                    displayCocktails(response)
                    //displayCocktails1(response1)
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

    private fun displayCocktails1(cocktails: List<popularDrinks>) {
        // Display cocktails using the received data
        cocktails.take(imageViews1.size).forEachIndexed { index, cocktail ->
            val imageView = imageViews1[index]
            Picasso.get()
                .load(cocktail.drinkImg)
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

    private fun showBarsDropDownMenu(anchorView: View) {
        val popupMenu = PopupMenu(requireContext(), anchorView)
        popupMenu.menuInflater.inflate(R.menu.nav_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.nav_service1 -> {
                    // Handle the Service 1 item click
                }
                R.id.nav_service2 -> {
                    // Handle the Service 2 item click
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun openUber() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("uber://")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("market://details?id=com.ubercab")
            startActivity(playStoreIntent)
        }
    }
/*
    private fun showBarsDropDownMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.nav_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.nav_service1 -> {
                    // Handle the Service 1 item click
                }
                R.id.nav_service2 -> {
                    // Handle the Service 2 item click
                }
            }
            true
        }
        popupMenu.show()
    }

 */
}


