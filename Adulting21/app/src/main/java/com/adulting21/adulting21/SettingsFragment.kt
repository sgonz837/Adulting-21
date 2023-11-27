package com.adulting21.adulting21

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


class SettingsFragment : Fragment() {


    //Here are the variables names for the calculator
    private var username: String? = null
    private var age: Int? = null
    private var drinkCount: Int? = null
    private var weight: Int? = null
    private var sex: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Retrieve user data from shared preference
        val sharedPref = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)

        username = sharedPref.getString("username", "DefaultUsername")
        age = sharedPref.getInt("age", 0)
        drinkCount = sharedPref.getInt("drinkCount", 0)
        weight = sharedPref.getInt("weight", 0)
        sex = sharedPref.getString("sex", "DefaultSex")

    }
    //Update BAC
    /* private fun updateBAC() {
         val bac = calcBAC()
         Log.d("TAG", "BAC: $bac")
     }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val drinkNum = arguments?.getInt("drinkNum", 2) ?: 1
        //val drinkNum = arguments?.getInt("drinkNum") ?: 1
        //Log.d("TAG", "drinkNum in SettingsFragment: $drinkNum")

        //Log.d("TAG", "Username: $username, Age: $age, Weight: $weight, Sex: $sex, DrinkNum :$drinkCount")

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
        speedometerView.speed = calcBAC()
    }
/*
    companion object {
        @JvmStatic
        fun newInstance(username: String, age: Int, weight: Int, sex: String,drinkNum: Int) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString("username", username)
                    putInt("age", age)
                    putInt("weight", weight)
                    putString("sex", sex)
                    putInt("drinkNum", drinkNum)
                }
            }
    }


 */

    //calcBAC test
    fun calcBAC(): Double {

        //r must be double in order to do multiplication
        val r: Double = if (sex.equals("M", ignoreCase = true)) 0.68 else 0.55

        //formula for bac calculation --> numDrinks, Weight, sex and hours are hard coded
        //need to multiply by 100 to tailer to meter numbers
        //val bac =  (((3.0 * 14.0 / (0.68 * (150 * 453.592))) * 100.0 - 1.0 * 0.015) * 100)

        //Test to make sure weight and sex are being passed
        val bac = (((3.0 * 14.0 / (r * (weight?.times(453.592)!!))) * 100.0 - 1.0 * 0.015) * 100)

        //formula after A.S. reccomendation for fixes
        //need to multiply by 100 to tailer to meter numbers
        //453.592 is 1 lb in kilograms
        //ISSUE: drinkNum not being passed
        //val bac =  ((((drinkNumm?.times(14.0) ?:1.0) / (r * (weight?.times(453.592)!!))) * 100.0 - 1.0 * 0.015) * 100)
        return if (bac < 0) 0.0 else bac
        //return 0.0
    }


}
