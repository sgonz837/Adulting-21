/*

package com.example.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrinkInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularDrinks()
            withContext(Dispatchers.Main) {
                val layoutIngredients = listOf(
                    R.id.imageIngredient1,
                    R.id.imageIngredient2,
                    R.id.imageIngredient3
                )

                for ((index, cocktail) in response.withIndex()) {
                    if (index < layoutIngredients.size) {
                        val layoutId = layoutIngredients[index]
                        val layout = view.findViewById<LinearLayout>(layoutId)

                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredients[index].imageUrl)
                            .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
                            .into(imageView)

                        layout.addView(imageView)
                    }
                }

                val textView1 = view.findViewById<TextView>(R.id.kevin2)
                textView1.text = response.joinToString(separator = "\n") { cocktail ->
                    cocktail.ingredients[0].name
                }

                val textView2 = view.findViewById<TextView>(R.id.kevin3)
                textView2.text = response.joinToString(separator = "\n") { cocktail ->
                    cocktail.ingredients[1].name
                }

                val ingredients = response[0].ingredients.map { ingredient ->
                    ingredient.name
                }
                val textView = view.findViewById<TextView>(R.id.kevin)
                textView.text = ingredients.joinToString("\n")
            }
        }
        return view
    }
}


 */




package com.example.adulting21

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrinkInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()

            //In order to correctly display the correct drink, we need the api code so we will send
            //and receive that api key all over
            val cocktailId = arguments?.getString("cocktailId", "")
            val drinkApi = arguments?.getInt(
                "drinkApiKey",
                0
            ) // 0 is a default value if the key is not found

            if (cocktailId != null) {
                Log.d("TAG", cocktailId)
            };
/*
            val response = when (drinkNum) {
                1 -> apiService.vodkaDrinks()
                2 -> apiService.ginDrinks()
                3 -> apiService.rumDrinks()
                4 -> apiService.tequilaDrinks()
                else -> emptyList() // Handle other cases as needed
            }

 */
            withContext(Dispatchers.Main) {
                val layoutIngredients = listOf(
                    R.id.imageIngredient1,
                    R.id.imageIngredient2,
                    R.id.imageIngredient3
                )

                for ((index, cocktail) in response.withIndex()) {
                    if (index < layoutIngredients.size) {
                        val layoutId = layoutIngredients[index]
                        val layout = view.findViewById<LinearLayout>(layoutId)

                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredients[index].imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView)

                        layout.addView(imageView)
                    }
                }

                val textView1 = view.findViewById<TextView>(R.id.kevin2)
                textView1.text = response.joinToString(separator = "\n") { "${it.ingredients[1].name}" }

                val textView2 = view.findViewById<TextView>(R.id.kevin3)
                textView2.text = response.joinToString(separator = "\n") { "${it.ingredients[2].name}" }

                val ingredients = response[0].ingredients.map { it.name }
                val textView = view.findViewById<TextView>(R.id.kevin)
                textView.text = ingredients.joinToString("\n")
            }
        }
        return view
    }
}



