import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView.OnItemSelectedListener
<<<<<<< Updated upstream:Adulting21/app/src/main/java/com/example/adulting21/MeterBac.kt
import com.example.adulting21.R
=======
import com.adulting21.adulting21.R
import com.adulting21.adulting21.SpeedometerView
import kotlin.math.max
>>>>>>> Stashed changes:Adulting21/app/src/main/java/com/adulting21/adulting21/MeterBac.kt

class MeterBac : Fragment() {

    private val TAG = "MeterBacFragment"
    private val PREFS_NAME = "MyPrefs"
    private val DRINK_COUNT_KEY = "drinkCount"
    private val SELECTED_DRINK_KEY = "selectedDrink"
    private val DRINK_LIST_KEY = "drinkList"
    private val START_TIME_KEY = "startTime"

    private lateinit var drinkListSelection: MutableList<String>

    //to show what drinks have been consumed from drinkList
    private lateinit var textViewSelectedDrinks: TextView

    // list of drinks
    private val drinkList = arrayListOf(
        "Select Drink",
        "Small Beer",
        "Can Beer",
        "Pint Beer",
        "Single Shot",
        "Double Shot"
    )

    // set values to drink selection
    private val drinkBACMap = mapOf(
        "Select Drink" to null,
        "Small Beer" to 8.0,
        "Can Beer" to 12.0,
        "Pint Beer" to 18.0,
        "Single Shot" to 1.5,
        "Double Shot" to 3.0
    )

    private lateinit var drinkSpinner: Spinner
    private lateinit var addDrinkButton: Button
    //start new session
    private lateinit var startNewSessionButton: Button
    private var drinkCount: Int = 0

    //capture when starting to drink
    private var startTime: Long = 0


    private var selectedDrink: String = "Select Drink"



    //Variables for sex input
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private var selectedSex: String = "Male" // Default sex

    //Variables for intering user weight
    private lateinit var editTextWeight: EditText
    private var userWeight: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b_a_c, container, false)

        //new session button
        startNewSessionButton = view.findViewById(R.id.startNewSessionButton)
        // When the first drink is consumed
        startTime = System.currentTimeMillis()

        //radio buttons for sex selection
        radioMale = view.findViewById(R.id.radioMale)
        radioFemale = view.findViewById(R.id.radioFemale)

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
            // Reset start time and drink count
            startTime = System.currentTimeMillis()
            drinkCount = 0

            // Save updated start time and drink count to SharedPreferences
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putLong(START_TIME_KEY, startTime)
            editor.putInt(DRINK_COUNT_KEY, drinkCount)
            editor.apply()

            // Notify the user that a new session has started
            Toast.makeText(
                requireContext(),
                "New drinking session started",
                Toast.LENGTH_SHORT
            ).show()
        }



        //to show consumed drinks from drinksList
        textViewSelectedDrinks = view.findViewById(R.id.textViewSelectedDrinks)
        textViewSelectedDrinks.text = "" // Set initial text to an empty string

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
                    val message = "$selectedDrink - BAC: $alcValue"
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


        // Need drink amount
        addDrinkButton.setOnClickListener {

            // Set the start time when the user adds the first drink
            if (startTime == 0L) {
                startTime = System.currentTimeMillis()
            }

            // Increment drink count and display it
            drinkCount++

            // Get user's weight from the EditText
            val weightText = editTextWeight.text.toString()
            userWeight = if (weightText.isNotEmpty()) weightText.toInt() else 0

            // Add the current drink to the list
            drinkListSelection.add("$selectedDrink - Alc Content: ${drinkBACMap[selectedDrink]}")

            // Update the TextView with the selected drinks
            updateSelectedDrinksTextView()

            Toast.makeText(
                requireContext(),
                "Drink Count: $drinkCount, Sex: $selectedSex, Weight: $userWeight",
                Toast.LENGTH_SHORT
            ).show()

            // Save updated drink count and selected drink to SharedPreferences
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



        // Set up other UI interactions, if needed

        return view
    }

    // Helper function to update the TextView with selected drinks
    private fun updateSelectedDrinksTextView() {
        val selectedDrinksText = drinkListSelection.joinToString("\n")
        textViewSelectedDrinks.text = "Selected Drinks:\n$selectedDrinksText"
    }

    override fun onResume() {
        super.onResume()

        // Reset the spinner to its default state when the fragment becomes visible
        drinkSpinner.setSelection(0)

        // Update the TextView with selected drinks when the fragment resumes
        updateSelectedDrinksTextView()
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
}
