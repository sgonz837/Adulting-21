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

class DrinkInfoFragment : Fragment(){
    override fun onCreateView(
        //honestly i don't know what this does but its required for a fragment file
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //attaches to the appropriate layout file
        val view = inflater.inflate(R.layout.drink_details, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()
            withContext(Dispatchers.Main) {

                val layout = view.findViewById<LinearLayout>(R.id.imageIngredient2)
                val layout1 = view.findViewById<LinearLayout>(R.id.imageIngredient)
                val layout2 = view.findViewById<LinearLayout>(R.id.imageIngredient1)
                val layout3 = view.findViewById<LinearLayout>(R.id.imageIngredient3)
                //layout.removeAllViews()

                response.forEachIndexed { index, cocktail ->
                    if (index == 0) {
                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredient2Img)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView)
                        layout.addView(imageView)
                        return@forEachIndexed
                    } else if (index == 1) {
                        val imageView1 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredient1Img)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView1)
                        layout1.addView(imageView1)
                        return@forEachIndexed
                    } else if (index == 2) {
                        val imageView2 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredient1Img)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView2)
                        layout2.addView(imageView2)
                        return@forEachIndexed
                    }




                    val textView1 = view.findViewById<TextView>(R.id.kevin2)
                    textView1.text = response.joinToString(separator = "\n") {
                        "${it.ingredient2}"
                    }

                    val textView2 = view.findViewById<TextView>(R.id.kevin3)
                    textView2.text = response.joinToString(separator = "\n") {
                        "${it.ingredient3}"
                    }

                    val ingredients = cocktail.ingredient1.split(", ")
                    val textView = view.findViewById<TextView>(R.id.kevin)
                    for (ingredient in ingredients) {
                        println(ingredient) // Do something with each ingredient

                    }
                }
            }


        }
        return view
    }
}

