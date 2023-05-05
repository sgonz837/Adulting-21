package com.example.adulting21

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class SearchFragment : Fragment(), OnMapReadyCallback, GoogleMap.InfoWindowAdapter,
    GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Set custom info window adapter
        mMap.setInfoWindowAdapter(this)
        mMap.setOnInfoWindowClickListener(this)
        // Add markers for local bars here
        val bar1 = LatLng(40.51611649753186, -75.7779062603749)
        mMap.addMarker(
            MarkerOptions().position(bar1).title("Shorty's Bar")
                .snippet("Night Club ")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bar1, 15f))
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null here to indicate that we are not using a default info window
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        // Inflate custom info window layout
        val infoWindow = layoutInflater.inflate(R.layout.info_window_layout, null)

        // Find views in info window layout
        val imageView: ImageView = infoWindow.findViewById(R.id.image_view78)
        val titleView: TextView = infoWindow.findViewById(R.id.title_view78)
        val snippetView: TextView = infoWindow.findViewById(R.id.snippet_view)

        // Set views with marker data
        imageView.setImageResource(R.drawable.baseline_person_24)
        titleView.text = marker.title
        snippetView.text = marker.snippet

        return infoWindow
    }

    override fun onInfoWindowClick(marker: Marker) {
        // Create a Uri from the marker location to use in the intent
        val uri = Uri.parse("google.navigation:q=${marker.position.latitude},${marker.position.longitude}")

        // Create an intent to launch Google Maps with directions to the marker location
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")

        // Start the intent
        startActivity(intent)
        /*
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:${marker.position.latitude},${marker.position.longitude}")
        startActivity(intent)

         */
    }
}

/*
class SearchFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add markers for local bars here
        // Add markers for local bars here
        val bar1 = LatLng(40.51611649753186, -75.7779062603749)
        mMap.addMarker(
            MarkerOptions().position(bar1).title("Local Bar 1")
                .snippet("Description of Local Bar 1")
        )
        // Zoom in to the location of the first bar marker
        //val zoomLevel = 15f
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bar1, zoomLevel))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bar1))
        // Set up info window adapter to customize the contents of the info window
        mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                return null // Use default info window background
            }

            override fun getInfoContents(marker: Marker): View {
                val view = layoutInflater.inflate(R.layout.info_window_layout, null)

                val titleTextView = view.findViewById<TextView>(R.id.title_view78)
                titleTextView.text = marker.title

                val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
                descriptionTextView.text = marker.snippet

                return view
            }
        })

        // Set click listener to show info window when marker is clicked
        mMap.setOnMarkerClickListener { marker ->
            marker.showInfoWindow()
            true
        }
    }

 */
    /*
        val bar2 = LatLng(37.7694, -122.4862)
        mMap.addMarker(
            MarkerOptions().position(bar2).title("Local Bar 2")
                .snippet("Description of Local Bar 2")
        )

         */
/*
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

 */

/*
class SearchFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}



 */

