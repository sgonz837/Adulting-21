package com.example.adulting21

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bottleDrinkInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class bottleDrinkInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var imageViews: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottle_drink_layout, container, false)
        val drinkNum = arguments?.getInt("drinkBottleNum", 0) // 0 is a default value if the key is not found


        //Log.d("TAG", drinkNum.toString())
        Log.d("TAG", drinkNum.toString())

        imageViews = listOf(
            view.findViewById(R.id.imageView14),
            view.findViewById(R.id.imageView15),
           // view.findViewById(R.id.imageView16),
            view.findViewById(R.id.imageView17)
                    /*
            view.findViewById(R.id.drinkImage5),
            view.findViewById(R.id.drinkImage6),
            view.findViewById(R.id.drinkImage7),
            view.findViewById(R.id.drinkImage8),
            view.findViewById(R.id.drinkImage9),
            view.findViewById(R.id.drinkImage10)

                     */
        )

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottle_drink_layout, container, false)
    }
}