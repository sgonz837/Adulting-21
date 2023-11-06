package com.example.adulting21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PanicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_panic, container, false)

        GlobalScope.launch(Dispatchers.IO) {
            // Add a button or any UI element that triggers the confirmation dialog
            //val confirmButton = view.findViewById(R.id.confirmButton) // Replace with your button ID
            /*
            confirmButton.setOnClickListener {
                showConfirmationDialog()
            }

             */

        }
        return view
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Action")
        builder.setMessage("Do you want to continue?")

        // Add positive button - User confirms the action
        builder.setPositiveButton("Yes") { dialog, which ->
            // Add the code to handle the user's confirmation here
            // For example, navigate to another fragment or perform some action
        }

        // Add negative button - User cancels the action
        builder.setNegativeButton("No") { dialog, which ->
            // Add code to handle the user's cancellation or simply dismiss the dialog
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}

