package com.example.adulting21

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val phoneNumberKey = "PHONE_NUMBER_KEY"
    private val emergencyContactNameKey = "EMERGENCY_CONTACT_NAME_KEY"

    private lateinit var firebaseAuth: FirebaseAuth

    var phoneNumber: String
        get() = sharedPreferences.getString(phoneNumberKey, "") ?: ""
        set(value) {
            sharedPreferences.edit().putString(phoneNumberKey, value).apply()
        }

    var emergencyContactName: String
        get() = sharedPreferences.getString(emergencyContactNameKey, "") ?: ""
        set(value) {
            sharedPreferences.edit().putString(emergencyContactNameKey, value).apply()
        }
}

class PanicFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_panic, container, false)

        val phoneNumberEditText = view.findViewById<EditText>(R.id.phoneNumberEditText)
        val emergencyContactNameEditText = view.findViewById<EditText>(R.id.emergencyContactNameEditText)
        val confirmButton = view.findViewById<Button>(R.id.confirmButton)

        // Set the EditTexts with the stored values
        phoneNumberEditText.setText(sharedViewModel.phoneNumber)
        emergencyContactNameEditText.setText(sharedViewModel.emergencyContactName)

        confirmButton.setOnClickListener {
            // Store the phone number and emergency contact name in the ViewModel
            sharedViewModel.phoneNumber = phoneNumberEditText.text.toString()
            sharedViewModel.emergencyContactName = emergencyContactNameEditText.text.toString()

            showConfirmationDialog()
        }
        return view
    }



    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Action")
        builder.setMessage("Do you want to continue?")

        builder.setPositiveButton("Yes") { dialog, which ->
            // Dial the phone number
            dialPhoneNumber(sharedViewModel.phoneNumber)
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        // Use an Intent to dial the phone number
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}

