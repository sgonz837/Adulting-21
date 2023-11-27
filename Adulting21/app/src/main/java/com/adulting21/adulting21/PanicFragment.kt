package com.adulting21.adulting21

<<<<<<< Updated upstream:Adulting21/app/src/main/java/com/example/adulting21/PanicFragment.kt
=======
import android.content.Intent
import android.net.Uri
>>>>>>> Stashed changes:Adulting21/app/src/main/java/com/adulting21/adulting21/PanicFragment.kt
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
<<<<<<< Updated upstream:Adulting21/app/src/main/java/com/example/adulting21/PanicFragment.kt
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
=======
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
>>>>>>> Stashed changes:Adulting21/app/src/main/java/com/adulting21/adulting21/PanicFragment.kt

class PanicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_panic, container, false)


        val confirmButton = view.findViewById<Button>(R.id.confirmButton)


        confirmButton.setOnClickListener {
                showConfirmationDialog()
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

