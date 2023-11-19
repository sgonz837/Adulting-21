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
import com.google.firebase.database.*

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val phoneNumberKey = "PHONE_NUMBER_KEY"
    private val emergencyContactNameKey = "EMERGENCY_CONTACT_NAME_KEY"
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")

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

    fun updateEmergencyContact(phoneNumber: String, contactName: String) {
        // Update the emergency contact info in the Firebase Realtime Database
        val user = FirebaseAuth.getInstance().currentUser
        val userId = user?.uid

        userId?.let {
            val userData = HashMap<String, Any>()
            userData["CotNumber"] = phoneNumber
            userData["CotName"] = contactName

            databaseReference.child(it).updateChildren(userData)
        }
    }
}

class PanicFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")

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
            val phoneNumber = phoneNumberEditText.text.toString()
            val contactName = emergencyContactNameEditText.text.toString()

            sharedViewModel.phoneNumber = phoneNumber
            sharedViewModel.emergencyContactName = contactName

            // Update the emergency contact info in the Firebase Realtime Database
            sharedViewModel.updateEmergencyContact(phoneNumber, contactName)

            showConfirmationDialog(phoneNumber)
        }
        return view
    }

    private fun showConfirmationDialog(phoneNumber: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Action")
        builder.setMessage("Do you want to continue?")

        builder.setPositiveButton("Yes") { dialog, which ->
            // Dial the stored phone number
            dialPhoneNumber(phoneNumber)
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

