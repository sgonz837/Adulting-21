package com.example.adulting21.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.adulting21.R


class DarkModeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dark_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)

        val lightModeButton = view.findViewById<RadioButton>(R.id.light_mode_button)
        val darkModeButton = view.findViewById<RadioButton>(R.id.dark_mode_button)

        // Check the current theme and set the appropriate radio button as checked
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        lightModeButton.isChecked = currentNightMode == Configuration.UI_MODE_NIGHT_NO
        darkModeButton.isChecked = currentNightMode == Configuration.UI_MODE_NIGHT_YES

        // Set a listener for the radio group to change the theme when a radio button is selected
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                lightModeButton.id -> {
                    // Set the theme to light mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                darkModeButton.id -> {
                    // Set the theme to dark mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
    }

}
