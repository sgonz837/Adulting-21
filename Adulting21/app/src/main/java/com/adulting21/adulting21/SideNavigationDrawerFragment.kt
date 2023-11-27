package com.adulting21.adulting21

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class SideNavigationDrawerFragment : Fragment() {

    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_side_navigation_drawer, container, false)

        navigationView = view.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item clicks here
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle home item click
                    // You can perform actions specific to the Home menu item here
                    true
                }
                R.id.nav_logout -> {
                    // Handle logout item click
                    // You can perform actions specific to the Logout menu item here
                    true
                }
                else -> false
            }
        }

        return view
    }
}
