package com.example.adulting21.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.adulting21.ProfileFragment
import com.example.adulting21.R

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsHub.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsHub : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_settings_hub, container, false)
        // Inflate the layout for this fragment

        val accountBtn = view.findViewById<Button>(R.id.account_info_btn)
        accountBtn.setOnClickListener {
            val fragmentSwitch = ProfileFragment()
            replaceFragment(fragmentSwitch)
            Log.d("TAG", "Button Pressed");
        }

        val darkModeBtn = view.findViewById<Button>(R.id.dark_mode_btn)
        darkModeBtn.setOnClickListener {
            Log.d("TAG", "Button Pressed");
            val fragmentSwitch = DarkModeFragment()
            replaceFragment(fragmentSwitch)
        }

        return view
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
