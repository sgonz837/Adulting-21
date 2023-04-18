/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */


package com.example.adulting21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.adulting21.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class HomeFragment : Fragment() {

    override fun onCreateView(
        //honestly i don't know what this does but its required for a fragment file
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //attaches to the appropriate layout file
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        /*
            GlobalScope.launch is the only place where we can api calls so any any all api function
            calls will happen here, if you were to implement something bad then the whole app will
            crash since its such a delicate built in function
         */
        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()
            withContext(Dispatchers.Main) {

                val layout = view.findViewById<LinearLayout>(R.id.layout1)
                layout.removeAllViews()

                val layout1 = view.findViewById<LinearLayout>(R.id.layout2)
                layout1.removeAllViews() //have to test this out

                val layout2 = view.findViewById<LinearLayout>(R.id.layout3)
                layout2.removeAllViews() //have to test this out

                val layout3 = view.findViewById<LinearLayout>(R.id.layout4)
                layout3.removeAllViews() //have to test this out

                val textView = view.findViewById<TextView>(R.id.text_view1)
                textView.text = response.joinToString(separator = "\n") {
                    "${it.name}"
                }

                /*
                    Just like in any other programming language, we can use a for loop, but we use
                    this for for loop through the data class that we set up in the cocktailapi file

                    index is the same as i in a for loop but we can use to to get what we need from
                    the data class file
                 */
                response.forEachIndexed { index, cocktail ->
                    if (index == 0) {
                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView)
                        layout.addView(imageView)
                        return@forEachIndexed
                    }

                    if (index == 1) {
                        val imageView1 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView1)
                        layout1.addView(imageView1)
                        return@forEachIndexed
                    }

                    if (index == 2) {
                        val imageView2 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView2)
                        layout2.addView(imageView2)
                        return@forEachIndexed
                    }
                    if (index == 3) {
                        val imageView3 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView3)
                        layout3.addView(imageView3)
                        return@forEachIndexed
                    }
                }
            }
        }
        return view
    }
}

