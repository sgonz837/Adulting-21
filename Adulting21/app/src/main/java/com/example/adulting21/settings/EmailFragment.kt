package com.example.adulting21.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.adulting21.R

class EmailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.nav_header, container, false)

        val textEmail: TextView = view.findViewById(R.id.textEmail)
        textEmail.setOnClickListener { sendEmail() }

        return view
    }

    private fun sendEmail() {
        val email = "adulting211@gmail.com"
        val subject = "Subject of your email"

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // Handle the case where no email app is available
            // You can display a toast or implement other error handling
        }
    }
}
