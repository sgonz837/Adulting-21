package com.example.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
                /*
                val layout = view.findViewById<LinearLayout>(R.id.imageIngredient2)
                //layout.removeAllViews()

                response.forEachIndexed { index, cocktail ->
                    if (index == 0) {
                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.ingredient2)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView)
                        layout.addView(imageView)
                        return@forEachIndexed
                    }
                    */


                }
            }


       // }

        return view
        }
    }

