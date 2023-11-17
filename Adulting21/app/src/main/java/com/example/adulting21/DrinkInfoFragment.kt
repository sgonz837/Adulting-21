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
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
    val favoritesRef = FirebaseDatabase.getInstance().reference.child("users").child(userUID ?: "").child("favorites")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

/*
        val favoriteButton = view.findViewById<ImageView>(R.id.FavoriteButton)

        favoriteButton.setOnClickListener(
            val bundles1 = Bundle();
            bundles1.putString("FavoriteIdKey", 1)
            bundles1.putString("FavoriteDrinkName", 1)

        )

 */
        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()

            // For Mike:
            // These two variable hold the id and drinkName
            val cocktailId = arguments?.getString("cocktailId", "")
            val cocktailName = arguments?.getString("drinkName", "")

            val favoriteButton = view.findViewById<Button>(R.id.FavoriteButton)

            //probbaly dont need these error checks, but they're here anyway
            if (cocktailName != null) {
                Log.d("TAG",cocktailName)
            }
            if (cocktailId != null && cocktailId.isNotEmpty()) {

                Log.d("TAG",cocktailId)

                //function when clicking favorite button
                favoriteButton.setOnClickListener {
                    val cocktailId = arguments?.getString("cocktailId", "")
                    val cocktailName = arguments?.getString("drinkName", "")

                    if (!cocktailId.isNullOrEmpty() && !cocktailName.isNullOrEmpty() && userUID != null) {
                        // Create a new node under the user's favorites with cocktailId as the key
                        favoritesRef.child(cocktailId).setValue(cocktailName)
                            .addOnSuccessListener {
                                // Successfully added favorite to the database
                                // You can add any UI updates or actions here if needed
                                Log.d("TAG", "Favorite added to database")
                            }
                            .addOnFailureListener {
                                // Failed to add favorite
                                Log.e("TAG", "Failed to add favorite to database: ${it.message}")
                            }
                    }
                }

                lifecycleScope.launch {
/*
                    apiService.searchCocktailsById(cocktailId, object : CocktailApiService.SearchCallback {
                        override fun onSearchSuccess(results: List<Cocktail>) {
                            // Handle successful result
                            if (results.isNotEmpty()) {
                                val firstCocktail = results[0]
                                withContext(Dispatchers.Main) {
                                    displayCocktailDetails(firstCocktail, view)
                                }
                            } else {
                                // Handle the case where no results are found
                            }
                        }

                        override fun onSearchError(error: String) {
                            // Handle error
                        }
                    })

 */
                    /*
                    apiService.searchCocktailsById(cocktailId, object : CocktailApiService.SearchCallback {
                        override fun onSearchSuccess(results: List<Cocktail>) {
                            // Handle successful result
                            displayCocktailDetails(results, view)
                        }

                        override fun onSearchSuccess(results: List<SearchResults>) {
                            displayCocktailDetails(results, view)

                        }

                        override fun onSearchError(error: String) {
                            TODO("Not yet implemented")
                        }

                        // Other methods...
                    })

                     */
                }
                /*
                apiService.searchCocktailsById(cocktailId, object : CocktailApiService.SearchCallback {
                    override fun onSearchSuccess(results: List<SearchResults>) {
                        // Handle successful result
                        withContext(Dispatchers.Main) {
                            displayCocktailDetails(result, view)
                        }
                    }

                    override fun onSearchError(error: String) {
                        // Handle search error
                    }

                    override fun onError(error: String) {
                        // Handle error
                        Log.e("TAG", "API Error: $error")
                    }
                })*/
            }
        }

        return view
    }

    private fun displayCocktailDetails(cocktail: Cocktail, view: View) {
        // Use the cocktail details to update the UI components in your layout
        // Example:
        val imageView = view.findViewById<ImageView>(R.id.cocktailImageView)
        Picasso.get()
            .load(cocktail.imageUrl)
            .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
            .into(imageView)

        val cocktailNameTextView = view.findViewById<TextView>(R.id.cocktailNameTextView)
        cocktailNameTextView.text = cocktail.name

        // Display ingredients
        val layoutIngredients = listOf(
            R.id.imageIngredient1,
            R.id.imageIngredient2,
            R.id.imageIngredient3
        )

        for ((index, ingredient) in cocktail.ingredients.withIndex()) {
            if (index < layoutIngredients.size) {
                val layoutId = layoutIngredients[index]
                val layout = view.findViewById<LinearLayout>(layoutId)

                val imageView = ImageView(requireContext())
                Picasso.get()
                    .load(ingredient.imageUrl)
                    .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
                    .into(imageView)

                layout.addView(imageView)
            }
        }
    }
}

/*
class DrinkInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()

            // Retrieve cocktail ID from arguments
            val cocktailId = arguments?.getString("cocktailId", "")
            val cocktailName = arguments?.getString("drinkName", "")

            if (cocktailName != null) {
                Log.d("TAG",cocktailName)
            }
            if (cocktailId != null) {
                Log.d("TAG", "Cocktail ID: $cocktailId")

                // Fetch cocktail details by ID
                val response = apiService.searchCocktailsById(cocktailId)

                withContext(Dispatchers.Main) {
                    // Display cocktail details in the UI
                    displayCocktailDetails(response, view)
                }
            }
        }
        return view
    }

    private fun displayCocktailDetails(cocktail: Cocktail, view: View) {
        // Display cocktail details using the received data
        val imageView = view.findViewById<ImageView>(R.id.drinkImage)
        Picasso.get()
            .load(cocktail.imageUrl)
            .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
            .into(imageView)

        val layoutIngredients = listOf(
            R.id.imageIngredient1,
            R.id.imageIngredient2,
            R.id.imageIngredient3
        )

        for ((index, ingredient) in cocktail.ingredients.withIndex()) {
            if (index < layoutIngredients.size) {
                val layoutId = layoutIngredients[index]
                val layout = view.findViewById<LinearLayout>(layoutId)

                val ingredientImageView = ImageView(requireContext())
                Picasso.get()
                    .load(ingredient.imageUrl)
                    .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
                    .into(ingredientImageView)

                layout.addView(ingredientImageView)
            }
        }

        val textView = view.findViewById<TextView>(R.id.drinkName)
        textView.text = cocktail.name
    }
}


 */
/*
class DrinkInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_details, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
           // val response = apiService.popularCocktails()

            //In order to correctly display the correct drink, we need the api code so we will send
            //and receive that api key all over
            val cocktailId = arguments?.getString("cocktailId", "")
            val cocktailName = arguments?.getString("drinkName", "")
            val drinkApi = arguments?.getInt(
                "drinkApiKey",
                0
            ) // 0 is a default value if the key is not found

            if (cocktailId != null) {
                Log.d("TAG", cocktailId)

            };
            if (cocktailName != null) {
                Log.d("TAG",cocktailName)
            }
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
/*
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

 */
/*
                val textView1 = view.findViewById<TextView>(R.id.kevin2)
                textView1.text = response.joinToString(separator = "\n") { "${it.ingredients[1].name}" }

                val textView2 = view.findViewById<TextView>(R.id.kevin3)
                textView2.text = response.joinToString(separator = "\n") { "${it.ingredients[2].name}" }

                val ingredients = response[0].ingredients.map { it.name }

 */
                val textView = view.findViewById<TextView>(R.id.kevin)
                //textView.text = ingredients.joinToString("\n")
            }
        }
        return view
    }
}

 */



