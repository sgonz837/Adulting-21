package com.adulting21.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo  // Import this
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SearchFragment : Fragment() {


        private lateinit var searchBar: EditText
        private lateinit var recyclerView: RecyclerView

        private val searchHandler = Handler(Looper.getMainLooper())
        private val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                val searchQuery = editable.toString().trim()
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
            val view = inflater.inflate(R.layout.fragment_search, container, false)
            searchBar = view.findViewById(R.id.search_bar)
            recyclerView = view.findViewById(R.id.search_results_recyclerview)

            searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("TAG", "Search Action Triggered")
                    performSearch(searchBar.text.toString().trim())
                    return@setOnEditorActionListener true
                } else {
                    Log.d("TAG", "Search Action Not Triggered. Action ID: $actionId")
                }
                false
            }

            searchBar.addTextChangedListener(textWatcher)

            return view
        }

        private fun performSearch(searchQuery: String) {
            Log.d("TAG", "Performing search for: $searchQuery")
            if (searchQuery.isNotEmpty()) {
                val apiService = CocktailApiService()

                val searchCallback = object : CocktailApiService.SearchCallback {
                    override fun onSearchSuccess(results: List<SearchResults>) {
                        activity?.runOnUiThread {
                            displaySearchResults(results)
                        }
                    }

                    override fun onSearchError(error: String) {
                        Log.e("TAG", "Search error: $error")
                    }
                }

                apiService.searchCocktails(searchQuery, searchCallback)
            } else {
                // Handle empty search query
                // You can show a message or a Toast indicating that the search query is empty
            }
        }

        private fun displaySearchResults(results: List<SearchResults>) {
            val adapter = SearchResultsAdapter(results)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        }
    }
