package com.example.adulting21

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ProfileFragment : Fragment() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var sexEditText: EditText
    private lateinit var drinkBox: EditText
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize the views
        nameEditText = view.findViewById(R.id.name)
        ageEditText = view.findViewById(R.id.age)
        weightEditText = view.findViewById(R.id.weight)
        sexEditText = view.findViewById(R.id.sex)
        drinkBox = view.findViewById(R.id.numDrinksBox)
        submitButton = view.findViewById(R.id.submitBtn)

        // Set a click listener for the Submit button
        submitButton.setOnClickListener {
            handleFormSubmission()
        }

        return view
    }

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

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

/*

class ProfileFragment : Fragment() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var sexEditText: EditText
    private lateinit var ecEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize the views
        nameEditText = view.findViewById(R.id.name)
        ageEditText = view.findViewById(R.id.age)
        weightEditText = view.findViewById(R.id.weight)
        sexEditText = view.findViewById(R.id.sex)
        ecEditText = view.findViewById(R.id.ec)
        submitButton = view.findViewById(R.id.submitBtn)

        // Set a click listener for the Submit button
        submitButton.setOnClickListener {
            handleFormSubmission()
        }



        return view
    }

    private fun handleFormSubmission() {
        val name = nameEditText.text.toString()
        val ageText = ageEditText.text.toString()
        val weightText = weightEditText.text.toString()
        val sex = sexEditText.text.toString()
        //val ec = ecEditText.text.toString()

        if (name.isNotEmpty() && ageText.isNotEmpty() && weightText.isNotEmpty() && sex.isNotEmpty() ) {

            val age = ageText.toInt()
            val weight = weightText.toInt()
            val bundle = Bundle()

            bundle.putString("username", name)
            bundle.putInt("age", age)
            bundle.putInt("weight", weight)
            bundle.putString("sex",sex)

            val settingsFragment = SettingsFragment()
            settingsFragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, settingsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            val message = "User data submitted successfully."
            showToast(message)
        } else {
            // Handle the case when any of the fields is empty (e.g., display an error message)
            val errorMessage = "Please fill in all fields."
            showToast(errorMessage)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}



 */