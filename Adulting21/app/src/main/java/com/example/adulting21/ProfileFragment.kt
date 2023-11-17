package com.example.adulting21

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class ProfileFragment : Fragment() {

    private lateinit var buttonCalculate: Button
    private lateinit var editTextDrinkPrice: EditText
    private lateinit var editTextPercentage: EditText
    private lateinit var textViewResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }
    // Initialize Firebase database reference
    private val databaseReference = FirebaseDatabase.getInstance().getReference("user_data")
    private fun handleFormSubmission() {
        val name = nameEditText.text.toString()
        val ageText = ageEditText.text.toString()
        val weightText = weightEditText.text.toString()
        val sex = sexEditText.text.toString()
        val drinkNum = drinkBox.text.toString()


        // Log the values to check if they are being retrieved correctly
        Log.d("ProfileFragment", "Name: $name, Age: $ageText, Weight: $weightText, Sex: $sex, drinkNumber: $drinkNum")

        if (name.isNotEmpty() && ageText.isNotEmpty() && weightText.isNotEmpty() && sex.isNotEmpty()) {
            val age = ageText.toInt()
            val weight = weightText.toInt()
            val number = drinkNum.toInt()
            // Create a shared preference
            val sharedPref = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPref.edit()

            // Save user data to the shared preference
            editor.putString("username", name)
            editor.putInt("age", age)
            editor.putInt("weight", weight)
            editor.putString("sex", sex)
            editor.putInt("drinkCount", number)

            // Apply changes
            editor.apply()

            val bundle = Bundle()
            bundle.putString("username", name)
            bundle.putInt("age", age)
            bundle.putInt("weight", weight)
            bundle.putString("sex", sex)
            bundle.putInt("drinkCount",number)

            // Create an instance of the SettingsFragment
            val settingsFragment = SettingsFragment()
            settingsFragment.arguments = bundle

            // Get the FragmentManager and start a transaction
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()

            // Replace the current fragment with the SettingsFragment
            transaction.replace(R.id.fragment_container, settingsFragment)

            // Add the transaction to the back stack, allowing the user to navigate back
            transaction.addToBackStack(null)

            // Save user data to Firebase
            val user = HashMap<String, Any>()
            user["username"] = name
            user["age"] = age
            user["weight"] = weight
            user["sex"] = sex
            user["drinkCount"] = number

            // Push data to Firebase
            databaseReference.push().setValue(user)

            // Commit the transaction
            transaction.commit()
            val message = "User data submitted successfully."
            showToast(message)
        } else {
            // Handle the case when any of the fields is empty (e.g., display an error message)
            val errorMessage = "Please fill in all fields."
            showToast(errorMessage)
        }
    }


    private fun calculateTotal() {
        // Get input values from EditText views
        val drinkPrice = editTextDrinkPrice.text.toString().toDoubleOrNull() ?: 0.0
        val percentage = editTextPercentage.text.toString().toDoubleOrNull() ?: 0.0

        // Calculate the tip amount
        val tipAmount = calculateTip(drinkPrice, percentage)

        // Display the result in the TextView, formatted to two decimal points
        textViewResult.text = "Based on desired tip percentage, you should tip your bartender at least: \$${"%.2f".format(tipAmount)}"
    }

    private fun calculateTip(price: Double, percentage: Double): Double {
        return price * (percentage / 100)
    }
}
