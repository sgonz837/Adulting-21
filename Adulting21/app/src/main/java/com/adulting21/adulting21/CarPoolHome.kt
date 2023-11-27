package com.adulting21.adulting21

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import java.util.Locale
import android.Manifest
import com.google.android.gms.location.LocationServices


private const val LOCATION_PERMISSION_REQUEST_CODE = 100

class CarPoolHome : Fragment(), OnMapReadyCallback, GoogleMap.InfoWindowAdapter,
    GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var searchBar: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_car_pool_home, container, false)
        searchBar = view.findViewById(R.id.search_bar)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Set the IME (Input Method Editor) options for the search bar
        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Set custom info window adapter
        mMap.setInfoWindowAdapter(this)
        mMap.setOnInfoWindowClickListener(this)

        // Request location permissions and display the current location on the map
        requestLocationPermissions()
    }

    private fun requestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permissions already granted, proceed to request location updates
            enableLocationUpdates()
        }
    }

    private fun enableLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true

            // Get the last known location (if available) and move the camera to it
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                }
            }
        }
    }

    private fun performSearch() {
        val location = searchBar.text.toString()
        if (location.isNotEmpty()) {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            try {
                val addresses = geocoder.getFromLocationName(location, 1)
                if (addresses != null) {
                    if (addresses.isNotEmpty()) {
                        val address = addresses[0]
                        val latLng = LatLng(address.latitude, address.longitude)
                        mMap.clear() // Clear previous markers
                        mMap.addMarker(MarkerOptions().position(latLng).title(address.featureName))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    } else {
                        Toast.makeText(requireContext(), "Location not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error searching location", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        } else {
            Toast.makeText(requireContext(), "Please enter a location", Toast.LENGTH_SHORT).show()
        }
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
    }

}


