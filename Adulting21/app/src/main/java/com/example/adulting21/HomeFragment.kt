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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            val apiService = CocktailApiService()
            val response = apiService.popularCocktails()
            withContext(Dispatchers.Main) {

                val layout = view.findViewById<LinearLayout>(R.id.layout1)
                layout.removeAllViews()
                //layout.orientation = LinearLayout.HORIZONTAL

                val textView = view.findViewById<TextView>(R.id.text_view1)
                textView.text = response.joinToString(separator = "\n") {
                    "${it.name}"
                }

                response.forEachIndexed { index, cocktail ->
                    if (index == 0) {
                        val imageView = ImageView(requireContext())
                        Picasso.get().load(cocktail.imageUrl).into(imageView)
                        layout.addView(imageView)
                        return@forEachIndexed
                    }
                }
            }
        }
        return view
    }
}