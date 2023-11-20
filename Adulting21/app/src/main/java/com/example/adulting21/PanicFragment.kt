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
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PanicFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private var currentUser: FirebaseUser? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_panic, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        val confirmCall = view.findViewById<Button>(R.id.confirmButton)
        val saveContact = view.findViewById<Button>(R.id.saveContact)



        confirmCall.setOnClickListener {
            showConfirmationDialog()
        }

        saveContact.setOnClickListener {
            saveToDB()
            Toast.makeText(requireContext(), "Contact Updated", Toast.LENGTH_SHORT).show()
        }
        // Load and display current user's emergency contact info
        loadEmergencyContactInfo(view)

        return view
    }

    private fun saveToDB() {
        val phoneNumber = view?.findViewById<EditText>(R.id.phoneNumber)?.text.toString()
        val contactName = view?.findViewById<EditText>(R.id.ContactName)?.text.toString()

        val userId = currentUser?.uid
        userId?.let {
            val userData = HashMap<String, Any>()
            userData["CotName"] = contactName
            userData["CotNumber"] = phoneNumber
            databaseReference.child(it).setValue(userData)
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Action")
        builder.setMessage("Do you want to continue?")

        builder.setPositiveButton("Yes") { dialog, which ->
            // Retrieve the contact number and dial it
            dialPhoneNumber()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun dialPhoneNumber() {
        val userId = currentUser?.uid
        userId?.let {
            databaseReference.child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userData = dataSnapshot.value as? Map<*, *>
                    val cotNumber = userData?.get("CotNumber") as? String

                    cotNumber?.let {
                        // Use an Intent to dial the phone number
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$cotNumber")
                        startActivity(intent)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
    }
    private fun loadEmergencyContactInfo(view: View) {
        val userId = currentUser?.uid
        userId?.let {
            databaseReference.child(it).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userData = dataSnapshot.value as? Map<*, *>
                    val cotName = userData?.get("CotName") as? String
                    val cotNumber = userData?.get("CotNumber") as? String

                    cotName?.let { name ->
                        view.findViewById<EditText>(R.id.ContactName)?.setText(name)
                    }

                    cotNumber?.let { number ->
                        view.findViewById<EditText>(R.id.phoneNumber)?.setText(number)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
    }
}



