package com.example.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo  // Import this
import android.widget.TextView.OnEditorActionListener  // Import this


class SearchFragment : Fragment() {
    // Declare a handler for debouncing the search input
    private val searchHandler = Handler(Looper.getMainLooper())


    private lateinit var searchBar: EditText
    private lateinit var searchButton: ImageView

    // TextWatcher for monitoring text changes in the EditText
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not needed for this case
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not needed for this case
        }

        override fun afterTextChanged(editable: Editable?) {
            val searchQuery = editable.toString().trim()
            // Delay the search by 500 milliseconds after the user stops typing
            searchHandler.removeCallbacksAndMessages(null)
            searchHandler.postDelayed({
                performSearch(searchQuery)
            }, 500)
        }
    }

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_search, container, false)

    searchBar = view.findViewById(R.id.search_bar)


    // Set an OnEditorActionListener for the search bar
    searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // Handle the "search" action here
            performSearch(searchBar.text.toString().trim())
            return@setOnEditorActionListener true  // Return true to indicate that you've handled the action
        }
        false
    }

    // Add the textWatcher to the search bar
    searchBar.addTextChangedListener(textWatcher)

    return view
}

    private fun performSearch(searchQuery: String) {
        val searchQuery = searchBar.text.toString().trim()

        if (searchQuery.isNotEmpty()) {
            val apiService = CocktailApiService()
            val searchResults = apiService.searchCocktails(searchQuery)

            // Process and display search results
            displaySearchResults(searchResults)
        } else {
            // Handle empty search query
            // You can show a message or a Toast indicating that the search query is empty
        }
    }

/*
    private fun performSearch(searchQuery: String) {
        val searchQuery = searchBar.text.toString().trim()
        if (searchQuery.isNotEmpty()) {
            val apiService = CocktailApiService()
            val searchResults = apiService.searchCocktails(searchQuery)

            displaySearchResults(searchResults)
        } else {
            // Handle empty search query
            // You can show a message or a Toast indicating that the search query is empty
        }
    }

 */
/*
    private fun performSearch() {
        val searchQuery = searchBar.text.toString().trim()

        if (searchQuery.isNotEmpty()) {
            val apiService = CocktailApiService()
            val searchResults = apiService.searchCocktails(searchQuery)

            // Process and display search results
            displaySearchResults(searchResults)
        } else {
            // Handle empty search query
            // You can show a message or a Toast indicating that the search query is empty
        }
    }

 */

    private fun displaySearchResults(results: List<SearchResults>) {
        val resultTextView = view?.findViewById<TextView>(R.id.search_results_textview)

        val resultText = results.joinToString("\n") { "${it.Name}: ${it.Image}" }

        if (resultTextView != null) {
            resultTextView.text = resultText
        }
    }

    /*
        private fun displaySearchResults(results: List<SearchResults>) {
            // You can replace this with your UI component (e.g., RecyclerView)
            val resultTextView = view?.findViewById<TextView>(R.id.search_results_textview)

            val resultText = results.joinToString("\n") { "${it.Name}: ${it.Image}" }

            if (resultTextView != null) {
                resultTextView.text = resultText
            }
        }

     */
/*
    private fun displaySearchResults(results: List<SearchResults>) {
        // You can replace this with your UI component (e.g., RecyclerView)
        val resultTextView = view.findViewById<TextView>(R.id.search_results_textview)

        val resultText = results.joinToString("\n") { "${it.resultName}: ${it.resultDescription}" }

        resultTextView.text = resultText
    }


 */

}


