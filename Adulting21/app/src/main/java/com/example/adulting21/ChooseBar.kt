package com.example.adulting21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseBar.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseBar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.choose_bar, container, false)

        val shortysBar: ImageView = view.findViewById(R.id.shortysBar)
        shortysBar.setOnClickListener {
            // Handle shortys image click, navigate to ShortysInfo Fragment
           // findNavController().navigate(R.id.action_chooseBar_to_shortysInfo)

        }
/*
        val ktownPub: ImageView = view.findViewById(R.id.ktownPub)
        ktownPub.setOnClickListener {
            // Handle ktownPub image click, navigate to KtownPubInfo fragment
            findNavController().navigate(R.id.ktownPub)
        }

        val kutzTavern: ImageView = view.findViewById(R.id.kutzTavern)
        kutzTavern.setOnClickListener {
            // Handle tavern image click, navigate to tavern page
            findNavController().navigate(R.id.kutzTavern)
        } */

        return view

    }






}