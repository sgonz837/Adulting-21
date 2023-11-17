/*Author: Savannah Crutchfield
Page to calculate drink and user input for BAC calculations
 */

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView.OnItemSelectedListener
import com.example.adulting21.R
import com.example.adulting21.SpeedometerView
import kotlin.math.max

class MeterBac : Fragment() {

    //Variables for Meter


    private val TAG = "MeterBacFragment"
    private val PREFS_NAME = "MyPrefs"
    private val DRINK_COUNT_KEY = "drinkCount"
    private val SELECTED_DRINK_KEY = "selectedDrink"
    private val DRINK_LIST_KEY = "drinkList"
    private val START_TIME_KEY = "startTime"

   private lateinit var drinkListSelection: MutableList<String>

    //to show what drinks have been consumed from drinkList
    //private lateinit var textViewSelectedDrinks: TextView

    // list of drinks
    private val drinkList = arrayListOf(
        "Beer",
        "Wine",
        "Single Shot",
        "Mixed Drink",
        "Cider"
    )

    // set values to drink selection
    private val drinkBACMap = mapOf(
        "Beer" to 14.0,
        "Wine" to 14.0,
        "Single Shot" to 14.0,
        "Mixed Drink" to 14.0,
        "Cider" to 14.0
    )

    private lateinit var drinkSpinner: Spinner
    private lateinit var addDrinkButton: Button
    //start new session
    private lateinit var startNewSessionButton: Button
    private var drinkCount: Int = 0

    //capture when starting to drink
    private var startTime: Long = 0


    private var selectedDrink: String = "Select Drink"

    //total alcohol content for drinks consumed
    private var totalAlcValue: Double = 0.0

    private var hoursElapsed: Double = 0.0



    //Variables for sex input
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private var selectedSex: String = "Male" // Default sex

    //Variables for intering user weight
    private lateinit var editTextWeight: EditText
    private var userWeight: Int = 0

    private val handler = Handler()
    private val updateIntervalMillis = 1000L // Update every 5 seconds

    private val updateRunnable: Runnable = object : Runnable {
        override fun run() {
            // Simulate a decrease in BAC over time
            //val metabolicRate = 0.015 //number per 1 hour
            //val metabolicRate = 0.0025 //number per 10 minutes
            val metabolicRate = 0.00025 //number per 1 minute
            totalAlcValue -= metabolicRate * hoursElapsed

            // Ensure BAC is non-negative
            totalAlcValue = max(0.0, totalAlcValue)

            // Calculate the new BAC value
            val newBAC = calcBAC(totalAlcValue, drinkCount, selectedSex, userWeight, hoursElapsed)

            // Update the BAC value on the TextView
            val bacValueTextView = view?.findViewById<TextView>(R.id.bacValueTextView)
            bacValueTextView?.text = "BAC: $newBAC%"

            // Update the BAC value on the speedometer
            val speedometerView = view?.findViewById<SpeedometerView>(R.id.speedometer)
            speedometerView?.speed = newBAC

            // Schedule the next update
            handler.postDelayed(this, updateIntervalMillis)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_a_c, container, false)

        //BAC Meter Code
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
        speedometerView.speed = calcBAC(totalAlcValue, drinkCount, selectedSex, userWeight, hoursElapsed)
        //speedometerView.speed = 20.0



        //new session button
        startNewSessionButton = view.findViewById(R.id.startNewSessionButton)
        // When the first drink is consumed
        startTime = System.currentTimeMillis()

        //radio buttons for sex selection
        radioMale = view.findViewById(R.id.radioMale)
        radioFemale = view.findViewById(R.id.radioFemale)

        val bacValueTextView = view.findViewById<TextView>(R.id.bacValueTextView)

        radioMale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedSex = "Male"
                radioFemale.isChecked = false
            }
        }

        radioFemale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedSex = "Female"
                radioMale.isChecked = false
            }
        }

        //Allow user to enter weight
        editTextWeight = view.findViewById(R.id.editTextWeight)

        // Retrieve the list of drinks from SharedPreferences
        val prefs: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        drinkCount = prefs.getInt(DRINK_COUNT_KEY, 0)
        selectedDrink = prefs.getString(SELECTED_DRINK_KEY, "Select Drink") ?: "Select Drink"
        drinkListSelection = prefs.getStringSet(DRINK_LIST_KEY, HashSet())?.toMutableList() ?: mutableListOf()
        startTime = prefs.getLong(START_TIME_KEY, 0)

        // Set up the "Start New Session" button
        startNewSessionButton.setOnClickListener {
            Log.d(TAG, "Start New Session button clicked")
            // Reset drink count and other relevant variables
            drinkCount = 0
            selectedDrink = "Select Drink"
            userWeight = 0
            selectedSex = "Male"
            drinkListSelection.clear() // Clear the list of consumed drinks

            // Reset BAC-related variables
            totalAlcValue = 0.0
            hoursElapsed = 0.0

            // Save updated data to SharedPreferences
            val editor: SharedPreferences.Editor = prefs.edit()
            if (startTime == 0L) {
                // Set the start time only if it's the first session
                startTime = System.currentTimeMillis()
                editor.putLong(START_TIME_KEY, startTime)
            }
            editor.putInt(DRINK_COUNT_KEY, drinkCount)
            editor.putString(SELECTED_DRINK_KEY, selectedDrink)
            editor.putStringSet(DRINK_LIST_KEY, HashSet(drinkListSelection))
            editor.putString("selectedSex", selectedSex)
            editor.putInt("userWeight", userWeight)
            editor.apply()

            // Log reset values
            Log.d(TAG, "After reset: drinkCount=$drinkCount, selectedDrink=$selectedDrink, totalAlcValue=$totalAlcValue")

            // Reset the displayed BAC
            bacValueTextView.text = "BAC: 0.0%"

            // Reset the speedometer
            speedometerView.speed = 0.0

            // Call the function to update the BAC value
            updateBACValue()

            // Notify the user that a new session has started
            Toast.makeText(
                requireContext(),
                "New drinking session started",
                Toast.LENGTH_SHORT
            ).show()
        }



        //to show consumed drinks from drinksList
       // textViewSelectedDrinks = view.findViewById(R.id.textViewSelectedDrinks)
       // textViewSelectedDrinks.text = "" // Set initial text to an empty string

        //Drink spinnner
        drinkSpinner = view.findViewById(R.id.spinner)
        addDrinkButton = view.findViewById(R.id.addDrinkbutton)

        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            drinkList
        )
        drinkSpinner.adapter = arrayAdapter

        drinkSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                view: View?,
                position: Int,
                p3: Long
            ) {
                selectedDrink = drinkList[position]
                val alcValue = drinkBACMap[selectedDrink]
                if (alcValue != null) {
                    val message = "$selectedDrink - Alcohol Content: $alcValue"
                    Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                    ).show()

                    // Log the message
                    Log.d(TAG, message)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Retrieve drink count and selected drink from SharedPreferences
        //val prefs: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        //drinkCount = prefs.getInt(DRINK_COUNT_KEY, 0)
        //selectedDrink = prefs.getString(SELECTED_DRINK_KEY, "Select Drink") ?: "Select Drink"


        // Have drink counter increment each time add drink button is pressed. Have alc content
        //value submit when button is pressed
        addDrinkButton.setOnClickListener {
            // Set the start time when the user adds the first drink
            if (startTime == 0L) {
                startTime = System.currentTimeMillis()

                //Start the periodic update when the user adds the first drink
                handler.postDelayed(updateRunnable, updateIntervalMillis)
            }

            // Increment drink count
            drinkCount++

            // Get user's weight from the EditText
            val weightText = editTextWeight.text.toString()
            userWeight = if (weightText.isNotEmpty()) weightText.toInt() else 0

            // Get the selected drink's alcohol content
            val alcValue: Double? = drinkBACMap[selectedDrink]

            // Update total alcohol content
            if (alcValue != null) {
                totalAlcValue += alcValue
            }

            // Calculate hours elapsed
            hoursElapsed = calcHoursElapsed()

            // Calculate the new BAC value
            val newBAC = calcBAC(totalAlcValue, drinkCount, selectedSex, userWeight, hoursElapsed)
            speedometerView.invalidate()

            // Log alcohol content
            Log.d(TAG, "Alcohol Content: ${totalAlcValue * drinkCount}")

            // Update the BAC value on the TextView
            bacValueTextView.text = "BAC: $newBAC%"

            // Update the TextView with the selected drinks (if needed)
            // updateSelectedDrinksTextView()

            // Update the BAC value on the speedometer
            speedometerView.speed = newBAC
            // Call the function to update the BAC value
            updateBACValue()

            Toast.makeText(
                requireContext(),
                "Drink Count: $drinkCount, Sex: $selectedSex, Weight: $userWeight",
                Toast.LENGTH_SHORT
            ).show()

            // Save updated data to SharedPreferences
            val prefs: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putLong(START_TIME_KEY, startTime)
            editor.putInt(DRINK_COUNT_KEY, drinkCount)
            editor.putString(SELECTED_DRINK_KEY, selectedDrink)
            editor.putStringSet(DRINK_LIST_KEY, HashSet(drinkListSelection))
            editor.putString("selectedSex", selectedSex)
            editor.putInt("userWeight", userWeight)
            editor.apply()
        }

        // Set the initial selected drink in the spinner
        val initialSelectedDrinkIndex = drinkList.indexOf(selectedDrink)
        drinkSpinner.setSelection(initialSelectedDrinkIndex)




        // Retrieve saved instance state
        savedInstanceState?.let {
            drinkCount = it.getInt(DRINK_COUNT_KEY, 0)
            selectedDrink = it.getString(SELECTED_DRINK_KEY, "Select Drink") ?: "Select Drink"
            drinkListSelection = it.getStringArrayList(DRINK_LIST_KEY)?.toMutableList() ?: mutableListOf()
            startTime = it.getLong(START_TIME_KEY, 0)
            userWeight = it.getInt("userWeight", 0)
            selectedSex = it.getString("selectedSex", "Male") ?: "Male"
            totalAlcValue = it.getDouble("totalAlcValue", 0.0)
            hoursElapsed = it.getDouble("hoursElapsed", 0.0)
        }


        return view
    }

    // Helper function to update the TextView with selected drinks
   /* private fun updateSelectedDrinksTextView() {
        val selectedDrinksText = drinkListSelection.joinToString("\n")
        textViewSelectedDrinks.text = "Selected Drinks:\n$selectedDrinksText"
    }*/

    override fun onResume() {
        super.onResume()

        // Reset the spinner to its default state when the fragment becomes visible
        drinkSpinner.setSelection(0)

        // Update the TextView with selected drinks when the fragment resumes
        //updateSelectedDrinksTextView()
        // Start the periodic update when the fragment resumes
        handler.postDelayed(updateRunnable, updateIntervalMillis)
    }

    override fun onPause() {
        super.onPause()

        // Stop the periodic update when the fragment is paused
        handler.removeCallbacks(updateRunnable)
    }



    fun calcHoursElapsed(): Double {
        // Retrieve the start time from SharedPreferences
        val prefs: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        startTime = prefs.getLong(START_TIME_KEY, 0)

        // Calculate the elapsed time in milliseconds
        val currentTime = System.currentTimeMillis()
        val elapsedTimeMillis = currentTime - startTime

        // Convert milliseconds to hours
        val hoursElapsed = elapsedTimeMillis / (1000 * 60 * 60).toDouble()

        return hoursElapsed
    }

    fun calcBAC(totalAlcValue: Double, drinkCount: Int, selectedSex: String, userWeight: Int, hoursElapsed: Double): Double {
        // Convert user weight to kilograms
        val weightKg = userWeight * 0.453592

        // Calculate BAC using Widmark Formula
        val r: Double = if (selectedSex.equals("Male", ignoreCase = true)) 0.68 else 0.55
        //val bacCalc = ((totalAlcValue * drinkCount / (r * weightKg)) - (hoursElapsed * 0.015) * 100)
        val bacCalc = ((((totalAlcValue * drinkCount) / (r * weightKg))) - (hoursElapsed * 0.015))

        // Log values for debugging
        Log.d(TAG, "totalAlcValue: $totalAlcValue, drinkCount: $drinkCount, selectedSex: $selectedSex, userWeight: $userWeight, hoursElapsed: $hoursElapsed, weightKg: $weightKg, bacCalc: $bacCalc")



        // Ensure BAC is non-negative
        return if (bacCalc < 0) 0.0 else bacCalc
    }

    // Function to update the BAC value
    private fun updateBACValue() {
        // Calculate the new BAC value
        val newBAC = calcBAC(totalAlcValue, drinkCount, selectedSex, userWeight, hoursElapsed)

        // Update the BAC value on the TextView
        val bacValueTextView = view?.findViewById<TextView>(R.id.bacValueTextView)
        if (bacValueTextView != null) {
            bacValueTextView.text = "BAC: $newBAC%"
        }

        // Update the BAC value on the speedometer
        val speedometerView = view?.findViewById<SpeedometerView>(R.id.speedometer)
        if (speedometerView != null) {
            speedometerView.speed = newBAC
        }
    }
}
