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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your UI elements
        buttonCalculate = view.findViewById(R.id.buttonCalculate)
        editTextDrinkPrice = view.findViewById(R.id.editTextDrinkPrice)
        editTextPercentage = view.findViewById(R.id.editTextPercentage)
        textViewResult = view.findViewById(R.id.textViewResult)

        // Set onClickListener for the Calculate button
        buttonCalculate.setOnClickListener { calculateTotal() }
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
