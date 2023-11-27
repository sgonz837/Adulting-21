package com.adulting21.adulting21

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FavoriteDrinksFragment : Fragment() {
    private lateinit var favoritesRef: DatabaseReference
    private lateinit var currentUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_drinks, container, false)

        // current user
        currentUser = FirebaseAuth.getInstance().currentUser ?: return view
        // Reference to favorites for the current user
        favoritesRef = FirebaseDatabase.getInstance().reference
            .child("users")
            .child(currentUser.uid)
            .child("favorites")


        val cocktailNamesListView: ListView = view.findViewById(R.id.cocktailNamesListView)
        val cocktailNamesList = mutableListOf<String>()

        val arrayAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            cocktailNamesList
        )
        cocktailNamesListView.adapter = arrayAdapter

        favoritesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cocktailNamesList.clear()

                for (drinkSnapshot in snapshot.children) {
                    val cocktailId = drinkSnapshot.key // Get cocktail ID
                    val cocktailName = drinkSnapshot.value // Get cocktail name

                    cocktailName?.let {
                        cocktailNamesList.add(it.toString())
                    }
                }

                // Notify the adapter that the data has changed
                arrayAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FavoritesFragment", "Failed to read favorites: ${error.message}")
            }
        })
        return view
    }
}