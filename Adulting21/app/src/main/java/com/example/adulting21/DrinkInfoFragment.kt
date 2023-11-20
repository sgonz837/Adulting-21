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
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class DrinkInfoFragment : Fragment() {
    val firebaseAuth = FirebaseAuth.getInstance()

    // current user UID
    val currentUser = firebaseAuth.currentUser
    val userUID = currentUser?.uid

    // Reference the "favorites" node in the database for the current user
    val favoritesRef = FirebaseDatabase.getInstance().reference.child("users").child(userUID ?: "")
        .child("favorites")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

        lifecycleScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()

            // For Mike:
            // These two variable hold the id and drinkName
            val cocktailId = arguments?.getString("cocktailId", "")
            val cocktailName = arguments?.getString("drinkName", "")

            val favoriteButton = view.findViewById<Button>(R.id.FavoriteButton)

            // Probably don't need these error checks, but they're here anyway
            if (cocktailName != null) {
                Log.d("TAG", cocktailName)
            }
            if (cocktailId != null && cocktailId.isNotEmpty()) {
                Log.d("TAG", cocktailId)

                // function when clicking the favorite button
                favoriteButton.setOnClickListener {
                    if (!cocktailId.isNullOrEmpty() && !cocktailName.isNullOrEmpty() && userUID != null) {
                        // Create a new node under the user's favorites with cocktailId as the key
                        favoritesRef.child(cocktailId).setValue(cocktailName)
                            .addOnSuccessListener {
                                // Successfully added favorite to the database
                                // You can add any UI updates or actions here if needed
                                Log.d("TAG", "Favorite added to the database")
                            }
                            .addOnFailureListener {
                                // Failed to add favorite
                                Log.e(
                                    "TAG",
                                    "Failed to add favorite to the database: ${it.message}"
                                )
                            }
                    }
                }
                lifecycleScope.launch(Dispatchers.Main) {
                    try {
                        val result = apiService.searchCocktailsById(
                            cocktailId,
                            object : CocktailApiService.SearchCallback1 {
                                override fun onSearchSuccess(result: Cocktail) {
                                    // Update UI with cocktail details
                                    lifecycleScope.launch {
                                        displayCocktailDetails(result, view)
                                    }
                                }

                                override fun onSearchError(error: String) {
                                    Log.e("TAG", "Error retrieving cocktail details: $error")
                                }
                            })
                    } catch (e: Exception) {
                        Log.e("TAG", "Error initiating cocktail search", e)
                    }
                }
            }
        }
        return view
    }
/*
    private suspend fun displayCocktailDetails(cocktail: Cocktail, view: View) {
        withContext(Dispatchers.Main) {
            val imageView = view.findViewById<ImageView>(R.id.cocktailImageView)
            Picasso.get()
                .load(cocktail.imageUrl)
                .resize(1000, 1000)
                .into(imageView)

            val cocktailNameTextView = view.findViewById<TextView>(R.id.cocktailNameTextView)
            cocktailNameTextView.text = cocktail.name

            val layoutIngredients = view.findViewById<LinearLayout>(R.id.layoutIngredients)

            // Assuming that cocktail.ingredients is a list of Ingredient objects
            for (ingredient in cocktail.ingredients) {
                val ingredientLayout = RelativeLayout(requireContext())
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.topMargin = 8 // Adjust this value as needed
                ingredientLayout.layoutParams = layoutParams

                val ingredientImageView = ImageView(requireContext())
                val imageLayoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                ingredientImageView.layoutParams = imageLayoutParams

                Picasso.get()
                    .load(ingredient.imageUrl)
                    .resize(1000, 1000)
                    .into(ingredientImageView)

                val ingredientNameTextView = TextView(requireContext())
                val nameLayoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
                nameLayoutParams.addRule(RelativeLayout.BELOW, ingredientImageView.id)
                nameLayoutParams.topMargin = 8 // Adjust this value as needed
                ingredientNameTextView.layoutParams = nameLayoutParams

                // Check if the measurement is not null before displaying it
                val displayText = if (ingredient.measure != null) {
                    "${ingredient.name}: ${ingredient.measure}"
                } else {
                    ingredient.name
                }

                ingredientNameTextView.text = displayText

                ingredientLayout.addView(ingredientImageView)
                ingredientLayout.addView(ingredientNameTextView)

                // Add the ingredient layout to the main layout
                layoutIngredients.addView(ingredientLayout)
            }
        }
    }

 */



        private suspend fun displayCocktailDetails(cocktail: Cocktail, view: View) {
            withContext(Dispatchers.Main) {
                val imageView = view.findViewById<ImageView>(R.id.cocktailImageView)
                Picasso.get()
                    .load(cocktail.imageUrl)
                    .resize(1000, 1000)
                    .into(imageView)

                val cocktailNameTextView = view.findViewById<TextView>(R.id.cocktailNameTextView)
                cocktailNameTextView.text = cocktail.name

                val layoutIngredients = listOf(
                    R.id.layoutIngredient1,
                    R.id.layoutIngredient2,
                    R.id.layoutIngredient3
                    // Add more IDs as needed based on your layout
                )

                val ingredientNamesLayout = view.findViewById<LinearLayout>(R.id.layoutIngredientNames)

                for ((index, ingredient) in cocktail.ingredients.withIndex()) {
                    if (index < layoutIngredients.size) {
                        val layoutId = layoutIngredients[index]
                        val layout = view.findViewById<RelativeLayout>(layoutId)

                        val ingredientImageView = ImageView(requireContext())
                        val layoutParams = RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                        )

                        ingredientImageView.id = View.generateViewId() // Generate a unique ID for ImageView
                        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
                        ingredientImageView.layoutParams = layoutParams

                        Picasso.get()
                            .load(ingredient.imageUrl)
                            .resize(1000, 1000)
                            .into(ingredientImageView)

                        val ingredientNameTextView = TextView(requireContext())
                        //ingredientNameTextView.text = "${ingredient.measure}${ingredient.name} "
                        val nameLayoutParams = RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                        )
                        nameLayoutParams.addRule(RelativeLayout.BELOW, ingredientImageView.id)
                        //nameLayoutParams.topMargin = 8 // Adjust this value to bring them closer
                        nameLayoutParams.topMargin = -300 // Adjust this value to bring them closer
                        nameLayoutParams.leftMargin = 100
                        ingredientNameTextView.layoutParams = nameLayoutParams


                        // Check if the measurement is not null before displaying it
                        val displayText = if (ingredient.measure != null) {
                            "${ingredient.measure} ${ingredient.name}"
                        } else {
                            ingredient.name
                        }

                        ingredientNameTextView.text = displayText

                        // Set text color to black
                        ingredientNameTextView.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))

                        layout.addView(ingredientImageView)
                        layout.addView(ingredientNameTextView)
                    }
                }
            }
        }



}