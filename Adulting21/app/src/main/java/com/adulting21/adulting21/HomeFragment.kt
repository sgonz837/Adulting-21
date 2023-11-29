/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */

package com.adulting21.adulting21


import android.content.Intent
import android.net.Uri
import MeterBac
import android.content.Context
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
import com.google.firebase.auth.FirebaseAuth
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


    // Declare TextViews at the beginning of your class
    private lateinit var randomDrinkName1: TextView
    private lateinit var randomDrinkName2: TextView
    private lateinit var randomDrinkName3: TextView
    private lateinit var randomDrinkName4: TextView
    private lateinit var randomDrinkName5: TextView
    private lateinit var randomDrinkName6: TextView
    private lateinit var randomDrinkName7: TextView
    private lateinit var randomDrinkName8: TextView
    private lateinit var randomDrinkName9: TextView
    private lateinit var randomDrinkName10: TextView

    private lateinit var randomDrinkName11: TextView
    private lateinit var randomDrinkName22: TextView
    private lateinit var randomDrinkName33: TextView
    private lateinit var randomDrinkName44: TextView
    private lateinit var randomDrinkName55: TextView
    private lateinit var randomDrinkName66: TextView
    private lateinit var randomDrinkName77: TextView
    private lateinit var randomDrinkName88: TextView
    private lateinit var randomDrinkName99: TextView
    private lateinit var randomDrinkName100: TextView
    private var drinkNum: Int = 1
    //private lateinit var toolbar: Toolbar

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

        // Find the toolbar in the fragment_home layout
       // toolbar = view.findViewById(R.id.toolbar)


        drawerLayout = view.findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            view.findViewById(R.id.toolbar),
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        // Set the navigation icon (drawable) for the Toolbar
        //val drawable = ContextCompat.getDrawable(this, R.drawable.baseline_person_24)
        //val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_person_24)

        //toolbar.navigationIcon = drawable


        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white, requireContext().theme)

        // toggle.drawerArrowDrawable.color = resources.getColor(R.color.white, theme)
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
                R.id.nav_logout -> {
                    // Handle logout item click
                    Toast.makeText(requireContext(), "Logout clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
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
        //var popularDrinks: List<popularDrinks> = null

        randomDrinkName1 = view.findViewById<TextView>(R.id.drinkName1)
        randomDrinkName2 = view.findViewById<TextView>(R.id.drinkName2)
       // val randomDrinkName1 = view.findViewById<TextView>(R.id.drinkName1)
       // val randomDrinkName2 = view.findViewById<TextView>(R.id.drinkName2)
        randomDrinkName3 = view.findViewById<TextView>(R.id.drinkName3)
        randomDrinkName4 = view.findViewById<TextView>(R.id.drinkName4)
        randomDrinkName5 = view.findViewById<TextView>(R.id.drinkName5)
        randomDrinkName6 = view.findViewById<TextView>(R.id.drinkName6)
        randomDrinkName7 = view.findViewById<TextView>(R.id.drinkName7)
        randomDrinkName8 = view.findViewById<TextView>(R.id.drinkName8)
        randomDrinkName9 = view.findViewById<TextView>(R.id.drinkName9)
        randomDrinkName10 = view.findViewById<TextView>(R.id.drinkName10)

        randomDrinkName11 = view.findViewById<TextView>(R.id.drinkName11)
        randomDrinkName22 = view.findViewById<TextView>(R.id.drinkName22)
        randomDrinkName33 = view.findViewById<TextView>(R.id.drinkName33)
        randomDrinkName44 = view.findViewById<TextView>(R.id.drinkName44)
        randomDrinkName55 = view.findViewById<TextView>(R.id.drinkName55)
        randomDrinkName66 = view.findViewById<TextView>(R.id.drinkName66)
        randomDrinkName77 = view.findViewById<TextView>(R.id.drinkName77)
        randomDrinkName88 = view.findViewById<TextView>(R.id.drinkName88)
        randomDrinkName99 = view.findViewById<TextView>(R.id.drinkName99)
        randomDrinkName100 = view.findViewById<TextView>(R.id.drinkName100)
        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            cocktails = apiService.popularCocktails()

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
        }

        val rumDrink = view.findViewById<ImageView>(R.id.rumBottleImage)
        rumDrink.setOnClickListener {
            val bundles3 = Bundle()
            bundles3.putInt("drinkBottleNum", 3) // You can put your desired value here

            val fragment3 = bottleDrinkInfo()
            fragment3.arguments = bundles3
            replaceFragment(fragment3)
        }

        val tequillaDrinkkk = view.findViewById<ImageView>(R.id.tequillaBottleImage)
        tequillaDrinkkk.setOnClickListener {
            val bundles4 = Bundle()
            bundles4.putInt("drinkBottleNum", 4) // You can put your desired value here

            val fragment4 = bottleDrinkInfo()
            fragment4.arguments = bundles4
            replaceFragment(fragment4)
        }

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        navigationView = view.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {

                    val fragmentSwitch = MeterBac()
                    replaceFragment(fragmentSwitch)
                    // Handle home item click
                    Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }

                R.id.nav_favorite -> {
                    val fragmentSwitch = FavoriteDrinksFragment()
                    replaceFragment(fragmentSwitch)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                R.id.nav_car_pool -> {
                    openUber()
                    // Handle home item click
//                    Toast.makeText(requireContext(), "Car pool clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }

                R.id.nav_share -> {
                    val profilePath = "https://play.google.com/store/apps/details?id=com.adulting21.adulting21"
                    val packageName = "com.google.android"
                    toAnotherAppOpen(requireContext(), profilePath, packageName)
                    // Handle home item click
//                    Toast.makeText(requireContext(), "Share App", Toast.LENGTH_SHORT).show()
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

                R.id.faq -> {
                    val fragmentSwitch = FAQFragment()
                    replaceFragment(fragmentSwitch)
                    // Handle home item click
                    Toast.makeText(requireContext(), "FAQ clicked", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    //return@setNavigationItemSelectedListener true

                    true
                }




                R.id.nav_bars_dropdown_trigger -> {
                    val fragmentSwitch = ChooseBar()
                    //val fragmentSwitch = MeterBac()
                    replaceFragment(fragmentSwitch)
                    // Handle the "Bars" item click by showing the dropdown menu
                    //showBarsDropDownMenu(view)
                    true
                }

                R.id.nav_logout -> {
                    // Handle logout item click
                    FirebaseAuth.getInstance().signOut() // Log out the user from Firebase
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent) // Redirect back to MainActivity

                    Toast.makeText(requireContext(), "Logged Out", Toast.LENGTH_SHORT).show()
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
                    viewModel.cachedCocktails1 = response1
                    displayCocktails(response)
                    displayCocktails1(response1)
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
            Log.d("DisplayCocktails", "Index: $index, Name: ${cocktail.name}, ImageURL: ${cocktail.imageUrl}")
            Picasso.get()
                .load(cocktail.imageUrl)
                .resize(
                    1000,
                    1000
                ) // Set width to 200 pixels and height to maintain aspect ratio
                .into(imageView)

            // Set click listener for each ImageView
            imageView.setOnClickListener {
                // Handle the click event
                val cocktailId = cocktail.id

                val bundle = Bundle()
                bundle.putString("cocktailId", cocktailId)
                bundle.putString("drinkName", cocktail.name)

                val drinkInfoFragment = DrinkInfoFragment()
                drinkInfoFragment.arguments = bundle

                replaceFragment(drinkInfoFragment)
            }

            // Set text for each randomDrinkName TextView
            when (index) {
                0 -> randomDrinkName1.text = cocktail.name
                1 -> randomDrinkName2.text = cocktail.name
                2 -> randomDrinkName3.text = cocktail.name
                3 -> randomDrinkName4.text = cocktail.name
                4 -> randomDrinkName5.text = cocktail.name
                5 -> randomDrinkName6.text = cocktail.name
                6 -> randomDrinkName7.text = cocktail.name
                7 -> randomDrinkName8.text = cocktail.name
                8 -> randomDrinkName9.text = cocktail.name
                9 -> randomDrinkName10.text = cocktail.name
            }
        }
    }

    private fun displayCocktails1(cocktails: List<Cocktail>) {
        // Display cocktails using the received data
        cocktails.take(imageViews1.size).forEachIndexed { index, cocktail ->
            val imageView = imageViews1[index]
            Log.d("DisplayCocktails", "Index: $index, Name: ${cocktail.name}, ImageURL: ${cocktail.imageUrl}")
            Picasso.get()
                .load(cocktail.imageUrl)
                .resize(
                    1000,
                    1000
                ) // Set width to 200 pixels and height to maintain aspect ratio
                .into(imageView)

            // Set click listener for each ImageView
            imageView.setOnClickListener {
                // Handle the click event
                val cocktailId = cocktail.id

                val bundle = Bundle()
                bundle.putString("cocktailId", cocktailId)
                bundle.putString("drinkName", cocktail.name)

                val drinkInfoFragment = DrinkInfoFragment()
                drinkInfoFragment.arguments = bundle

                replaceFragment(drinkInfoFragment)
            }

            // Set text for each randomDrinkName TextView
            when (index) {
                0 -> randomDrinkName11.text = cocktail.name
                1 -> randomDrinkName22.text = cocktail.name
                2 -> randomDrinkName33.text = cocktail.name
                3 -> randomDrinkName44.text = cocktail.name
                4 -> randomDrinkName55.text = cocktail.name
                5 -> randomDrinkName66.text = cocktail.name
                6 -> randomDrinkName77.text = cocktail.name
                7 -> randomDrinkName88.text = cocktail.name
                8 -> randomDrinkName99.text = cocktail.name
                9 -> randomDrinkName100.text = cocktail.name
            }
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
}


