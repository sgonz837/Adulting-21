package com.adulting21.adulting21


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class bottleDrinkInfo : Fragment() {

    private lateinit var imageViews: List<ImageView>
    private lateinit var loadingLayout: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottle_drink_layout, container, false)

        // Initialize the loading layout
        loadingLayout = view.findViewById(R.id.loadingLayout)
        // Initially, set the loading layout as invisible
        loadingLayout.visibility = View.GONE


        // Inside the onCreateView method, before making the API call
        loadingLayout.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()

            val drinkNum = arguments?.getInt(
                "drinkBottleNum",
                0
            ) // 0 is a default value if the key is not found

            val response = when (drinkNum) {
                1 -> apiService.vodkaDrinks()
                2 -> apiService.ginDrinks()
                3 -> apiService.rumDrinks()
                4 -> apiService.tequilaDrinks()
                else -> emptyList() // Handle other cases as needed
            }

            withContext(Dispatchers.Main) {
                imageViews = listOf(
                    view.findViewById(R.id.drinkImages1),
                    view.findViewById(R.id.drinkImages2),
                    view.findViewById(R.id.drinkImages3),
                    view.findViewById(R.id.drinkImages4),
                    view.findViewById(R.id.drinkImages5),
                    view.findViewById(R.id.drinkImages6)
                )
                val textView1 = view.findViewById<TextView>(R.id.bottleDrinkName1)
                val textView2 = view.findViewById<TextView>(R.id.bottleDrinkName2)
                val textView3 = view.findViewById<TextView>(R.id.bottleDrinkName3)
                val textView4 = view.findViewById<TextView>(R.id.bottleDrinkName4)
                val textView5 = view.findViewById<TextView>(R.id.bottleDrinkName5)
                val textView6 = view.findViewById<TextView>(R.id.bottleDrinkName6)
                withContext(Dispatchers.Main) {
                    response.forEachIndexed { index, cocktail ->
                        when (index) {
                            0 -> {
                                textView1.text = cocktail.drinkName
                                val drinkImg1 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg1)
                            }

                            1 -> {
                                textView2.text = cocktail.drinkName
                                val drinkImg2 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg2)
                            }

                            2 -> {
                                textView3.text = cocktail.drinkName
                                val drinkImg3 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg3)
                            }

                            3 -> {
                                textView4.text = cocktail.drinkName
                                val drinkImg4 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg4)
                            }

                            4 -> {
                                textView5.text = cocktail.drinkName
                                val drinkImg5 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg5)
                            }

                            5 -> {
                                textView6.text = cocktail.drinkName
                                val drinkImg6 = imageViews[index] // Get the ImageView
                                Picasso.get()
                                    .load(cocktail.drinkImg)
                                    .resize(1000, 1000)
                                    .into(drinkImg6)
                            }
                        }
                    }
                }

            }
        }

        // Use a coroutine to delay for 2 seconds on the main thread
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000) // 2 seconds
            loadingLayout.visibility = View.GONE
        }
        return view
    }

}