package com.example.adulting21

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
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
        // Fetch favorite drinks data
/*
        val cocktailNamesTextView: TextView = view.findViewById(R.id.cocktailNamesTextView)
        val cocktailNamesList = mutableListOf<String>()

        favoritesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (drinkSnapshot in snapshot.children) {
                    val cocktailId = drinkSnapshot.key // Get cocktail ID
                    val cocktailName = drinkSnapshot.value // Get cocktail name

                    // cocktailId and cocktailName, use them as needed
                    Log.d("FavoritesFragment", "Cocktail ID: $cocktailId, Name: $cocktailName")

                    cocktailName?.let {
                        cocktailNamesList.add(it.toString())
                    }
                }
                // Update the UI with the cocktail names
                val cocktailNames = cocktailNamesList.joinToString(", ")
                cocktailNamesTextView.text = cocktailNames
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FavoritesFragment", "Failed to read favorites: ${error.message}")
            }
        })

        return view

 */
    }
}
//package com.example.adulting21
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [FavoriteDrinksFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class FavoriteDrinksFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_favorite_drinks, container, false)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val apiService = CocktailApiService()
//
//            // For Mike:
//            // These two variable hold the id and drinkName
//            // Use as you wish
//            val cocktailId = arguments?.getString("cocktailId", "")
//
//            val cocktailName = arguments?.getString("drinkName", "")
//
//            if (cocktailId != null) {
//                Log.d("TAG", "Favorite Id $cocktailId")
//            }
//            if (cocktailName != null) {
//                Log.d("TAG", "Favorite drinks Name $cocktailName")
//            }
//        }
//        // Inflate the layout for this fragment
//        //return inflater.inflate(R.layout.fragment_favorite_drinks, container, false)
//        return view
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FavoriteDrinksFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FavoriteDrinksFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}