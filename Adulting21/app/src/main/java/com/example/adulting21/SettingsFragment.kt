package com.example.adulting21

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//import com.github.anastr.speedviewlib.SpeedometerView // Uncomment this import
//Code has been pulled from https://github.com/codeplayon/AndroidSpeedometer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val speedometerView = view.findViewById<SpeedometerView>(R.id.speedometer)

        speedometerView.setLabelConverter(object : SpeedometerView.LabelConverter {
            override fun getLabelFor(progress: Double, maxProgress: Double): String {
                return (progress.toInt()).toString()
            }
        })

        // configure value range and ticks
        speedometerView.maxSpeed = 40.0
        speedometerView.majorTickStep = 2.0
        speedometerView.minorTicks = 0

        // Configure value range colors
        speedometerView.addColoredRange(0.0, 6.0, Color.GREEN)
        speedometerView.addColoredRange(6.0, 20.0, Color.YELLOW)
        speedometerView.addColoredRange(20.0, 40.0, Color.RED)

        // Set the speed
        speedometerView.speed = 25.0
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
