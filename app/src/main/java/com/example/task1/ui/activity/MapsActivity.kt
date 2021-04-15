package com.example.task1.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.task1.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Thread.sleep

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager : LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var latLong : LatLng
    private val MIN_TIME :Long = 1000
    private val MIN_DIST :Float = 5F
    private val tag = "MapsActivity"
    private var latLng : LatLng ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        ActivityCompat.requestPermissions(this, arrayOf<String>(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
            PackageManager.PERMISSION_GRANTED)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val currentLocation = HomeActivity.coordinates
        mMap.addMarker(MarkerOptions().position(currentLocation!!).title("Your location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation))

        latLng =HomeActivity.coordinates

        mMap.setOnMapClickListener {
            val newMarkerOptions = MarkerOptions()
            newMarkerOptions.position(it)
            newMarkerOptions.title("Your location")
            latLng = it
            mMap.clear()
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 6F))
            mMap.addMarker(newMarkerOptions)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,HomeActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("LatLng",latLng.toString())
        HomeActivity.coordinates = latLng
        startActivity(intent)
    }
}