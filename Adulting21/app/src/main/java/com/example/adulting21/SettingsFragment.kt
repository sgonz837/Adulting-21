package com.example.adulting21

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


class SettingsFragment : Fragment() {

    //Here are the variables names for the calculator
    private var username: String? = null
    private var age: Int? = null
    private var drinkNum: Int? = null
    private var weight: Int? = null
    private var sex: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve user data from shared preference
        val sharedPref = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)

        username = sharedPref.getString("username", "DefaultUsername")
        age = sharedPref.getInt("age", 0)
        drinkNum = sharedPref.getInt("drinkNum", 0)
        weight = sharedPref.getInt("weight", 0)
        sex = sharedPref.getString("sex", "DefaultSex")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("TAG", "Username: $username, Age: $age, Weight: $weight, Sex: $sex")

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
        @JvmStatic
        fun newInstance(username: String, age: Int, weight: Int, sex: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString("username", username)
                    putInt("age", age)
                    putInt("weight", weight)
                    putString("sex", sex)
                }
            }
    }

}
