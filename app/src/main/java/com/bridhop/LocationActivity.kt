package com.bridhop

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var googleApiClient: GoogleApiClient? = null
    private var locationManager: LocationManager? = null
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val isLocationEnabled: Boolean
        get(){
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            return locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true || locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val backButton = findViewById<Button>(R.id.back)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.location) as SupportMapFragment

        supportMapFragment.getMapAsync(this)

        googleApiClient = GoogleApiClient.Builder(this)
                                         .addConnectionCallbacks(this)
                                         .addOnConnectionFailedListener(this)
                                         .addApi(LocationServices.API)
                                         .build()

        locationCallback = object : LocationCallback(){}
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        checkLocation()
        startLocationUpdates()
    }

    override fun onStart() {
        super.onStart()

        googleApiClient?.connect()
    }

    override fun onConnected(bundle: Bundle?) {
        if(
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        googleApiClient?.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i("onConnectionFailed", connectionResult.toString())
    }

    override fun onLocationChanged(location: Location) {
        Log.i("onLocationChanged", location.toString())
    }

    override fun onMapReady(map: GoogleMap) {
        updateLocation(map, null)
    }

    private fun checkLocation(): Boolean{
        return isLocationEnabled
    }

    private fun startLocationUpdates(){
        locationRequest = LocationRequest.create()
                                         .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                         .setInterval(20000)
                                         .setFastestInterval(2000)

        if(
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) return

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        fusedLocationClient.lastLocation.addOnSuccessListener{ location: Location? ->
            location?.let{
                val latitude = location.latitude
                val longitude = location.longitude
                val message = "Updated location: $latitude and $longitude"

                Toast.makeText(this, message, Toast.LENGTH_LONG).show()

                val latLng = LatLng(latitude, longitude)

                updateLocation(googleMap, latLng)
            } ?: Toast.makeText(this, "Location not detected", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateLocation(map: GoogleMap, latLng: LatLng?){
        googleMap = map

        val hasLatLng = latLng != null
        val title = if(hasLatLng) "Me" else "Schornstein Cervejaria"
        val snippet = if(hasLatLng) "My location" else "Cervejaria em Pomerode (Santa Catarina)"
        val location = latLng ?: LatLng(-26.7384462, -49.1736941)
        val bitmap = arrayOf(0f, 30f, 60f, 120f, 180f, 210f, 240f, 270f, 300f, 330f)
        val bitmapSorted = bitmap[(Math.random() * bitmap.size).toInt()]

        googleMap.addMarker(
            MarkerOptions().position(location)
                           .title(title)
                           .snippet(snippet)
                           .icon(BitmapDescriptorFactory.defaultMarker(bitmapSorted))
        )

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.5f))

        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                Log.i("getInfoWindow", marker.toString())

                return null
            }

            override fun getInfoContents(marker: Marker): View {
                val info = LinearLayout(applicationContext)

                info.orientation = LinearLayout.VERTICAL

                val textViewTitle = TextView(applicationContext)

                textViewTitle.setTextColor(Color.BLACK)
                textViewTitle.gravity = Gravity.START
                textViewTitle.setTypeface(null, Typeface.BOLD)
                textViewTitle.text = marker.title

                val textViewSnippet = TextView(applicationContext)

                textViewSnippet.setTextColor(Color.GRAY)
                textViewSnippet.text = marker.snippet

                info.addView(textViewTitle)
                info.addView(textViewSnippet)

                return info
            }
        })
    }
}
