package com.example.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MocktailFragment : Fragment() {
    private lateinit var imageViews: List<ImageView>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mocktail, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.mocktails()
            withContext(Dispatchers.Main) {
                val layoutIngredients = listOf(
                    R.id.mocktail_img_1,
                    R.id.mocktail_img_2,
                    R.id.mocktail_img_3,
                    R.id.mocktail_img_4,
                    R.id.mocktail_img_5,
                    R.id.mocktail_img_6,
                    R.id.mocktail_img_7,
                    R.id.mocktail_img_8
                )

                imageViews = listOf(
                    view.findViewById(R.id.mocktail_img_1),
                    view.findViewById(R.id.mocktail_img_2),
                    view.findViewById(R.id.mocktail_img_3),
                    view.findViewById(R.id.mocktail_img_4),
                    view.findViewById(R.id.mocktail_img_5),
                    view.findViewById(R.id.mocktail_img_6),
                    view.findViewById(R.id.mocktail_img_7),
                    view.findViewById(R.id.mocktail_img_8)
                )


                for ((index, mocktail) in response.withIndex()) {
                    if (index < layoutIngredients.size) {
                        val layoutId = layoutIngredients[index]
                        val imageView = imageViews[index]

                        // Load the image into the ImageView using Picasso
                        Picasso.get()
                            .load(mocktail.drinkImg) // Replace with the actual URL of the image
                            .resize(275, 275) // Set width to 1000 pixels and height to maintain aspect ratio
                            .into(imageView)
                    }
                }

                /*
                for ((index, mocktail) in response.withIndex()) {
                    if (index < layoutIngredients.size) {
                        val layoutId = layoutIngredients[index]
//                        val layout = view.findViewById<ConstraintLayout>(layoutId)

                        val imageView = ImageView(requireContext())
                        Picasso.get()
                            .load(mocktail.drinkName)
                            .resize(1000, 1000) // Set width to 1000 pixels and height to maintain aspect ratio
                            .into(imageView)

//                        layout.addView(imageView)
                    }
                }

                 */

                val textView1 = view.findViewById<TextView>(R.id.mocktail_text_1)
//                textView1.text = response.joinToString(separator = "\n") { cocktail ->
//                    cocktail.ingredients[0].name
//                }

                val textView2 = view.findViewById<TextView>(R.id.mocktail_text_2)
//                textView2.text = response.joinToString(separator = "\n") { cocktail ->
//                    cocktail.ingredients[1].name
//                }

//                val ingredients = response[0].ingredients.map { ingredient ->
//                    ingredient.name
//                }
                val textView3 = view.findViewById<TextView>(R.id.mocktail_text_3)
//                textView.text = ingredients.joinToString("\n")

                val textView4 = view.findViewById<TextView>(R.id.mocktail_text_4)

                val textView5 = view.findViewById<TextView>(R.id.mocktail_text_5)

                val textView6 = view.findViewById<TextView>(R.id.mocktail_text_6)

                val textView7 = view.findViewById<TextView>(R.id.mocktail_text_7)

                val textView8 = view.findViewById<TextView>(R.id.mocktail_text_8)

                withContext(Dispatchers.Main) {
                    response.forEachIndexed { index, mocktail ->
                        when (index) {
                            0 -> {
                                textView1.text = mocktail.drinkName

                            }

                            1 -> {
                                textView2.text = mocktail.drinkName

                            }

                            2 -> {
                                textView3.text = mocktail.drinkName
                            }

                            3 -> {
                                textView4.text = mocktail.drinkName

                            }

                            4 -> {
                                textView5.text = mocktail.drinkName

                            }

                            5 -> {
                                textView6.text = mocktail.drinkName

                            }

                            6 -> {
                                textView7.text = mocktail.drinkName
                            }

                            7 -> {
                                textView8.text = mocktail.drinkName

                            }
                        }
                    }
                }

//                for (index in 0 until imageViews.size) {
//                    val imageView = imageViews[index]
//                    imageView.setOnClickListener {
//                        replaceFragment(MocktailFragment())
//                    }
//                }
            }
        }
        return view
    }
}


//package com.example.adulting21
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [MocktailFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class MocktailFragment : Fragment() {
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
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_mocktail, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment MocktailFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MocktailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}