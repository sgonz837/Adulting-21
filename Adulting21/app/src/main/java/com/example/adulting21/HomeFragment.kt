/*
    Author: Sayan Gonzalez
    Description: Code for homepage aspects will go here.
                 What i done is set up a layout format to hold whatever api calls that we will do
                 then display it using the layout page
 */


package com.example.adulting21

import android.content.Intent
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
                layout1.removeAllViews()

                val layout2 = view.findViewById<LinearLayout>(R.id.layout3)
                layout2.removeAllViews()

                val layout3 = view.findViewById<LinearLayout>(R.id.layout4)
                layout3.removeAllViews()

                val layout4 = view.findViewById<LinearLayout>(R.id.layout5)
                layout4.removeAllViews()

                val layout5 = view.findViewById<LinearLayout>(R.id.layout6)
                layout5.removeAllViews()

                val layout6 = view.findViewById<LinearLayout>(R.id.layout7)
                layout6.removeAllViews()

                val layout7 = view.findViewById<LinearLayout>(R.id.layout8)
                layout7.removeAllViews()

                val layout8 = view.findViewById<LinearLayout>(R.id.layout9)
                layout8.removeAllViews()

                val layout9 = view.findViewById<LinearLayout>(R.id.layout10)
                layout9.removeAllViews()
                /*
                val textView = view.findViewById<TextView>(R.id.text_view1)
                textView.text = response.joinToString(separator = "\n") {
                    "${it.name}"
                }

                 */

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

                    if (index == 4) {
                        val imageView4 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView4)
                        layout4.addView(imageView4)
                        return@forEachIndexed
                    }

                    if (index == 5) {
                        val imageView5 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView5)
                        layout5.addView(imageView5)
                        return@forEachIndexed
                    }

                    if (index == 6) {
                        val imageView6 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView6)
                        layout6.addView(imageView6)
                        return@forEachIndexed
                    }

                    if (index == 7) {
                        val imageView7 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView7)
                        layout7.addView(imageView7)
                        return@forEachIndexed
                    }

                    if (index == 8) {
                        val imageView8 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView8)
                        layout8.addView(imageView8)
                        return@forEachIndexed
                    }
                    if (index == 9) {
                        val imageView9 = ImageView(requireContext())
                        Picasso.get()
                            .load(cocktail.imageUrl)
                            .resize(1000, 1000) // Set width to 200 pixels and height to maintain aspect ratio
                            .into(imageView9)
                        layout9.addView(imageView9)
                        return@forEachIndexed
                    }
                }

            }
        }
        /*
        val button = view.findViewById<LinearLayout>(R.id.layout2)
        button.setOnClickListener {
            val intent = Intent(activity, DrinkInfoFragment::class.java)
            startActivity(intent)
        }

         */

        val button = view.findViewById<LinearLayout>(R.id.layout2)
        button.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button1 = view.findViewById<LinearLayout>(R.id.layout1)
        button1.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button2 = view.findViewById<LinearLayout>(R.id.layout3)
        button2.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button3 = view.findViewById<LinearLayout>(R.id.layout4)
        button3.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button4 = view.findViewById<LinearLayout>(R.id.layout5)
        button4.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button5 = view.findViewById<LinearLayout>(R.id.layout6)
        button5.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button6 = view.findViewById<LinearLayout>(R.id.layout7)
        button6.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button7 = view.findViewById<LinearLayout>(R.id.layout8)
        button7.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button8 = view.findViewById<LinearLayout>(R.id.layout9)
        button8.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }

        val button9 = view.findViewById<LinearLayout>(R.id.layout10)
        button9.setOnClickListener {
            replaceFragment(DrinkInfoFragment());
        }


        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}

