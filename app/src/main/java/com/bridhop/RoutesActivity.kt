package com.bridhop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RoutesActivity : AppCompatActivity(), OnMapReadyCallback {
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routes)

        val backButton = findViewById<Button>(R.id.back)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val schornsteinLocation = LatLng(-26.7384462, -49.1736941)
        val omasHausLocation = LatLng(-26.9133562, -49.076877)

        googleMap?.addMarker(
            MarkerOptions().position(schornsteinLocation)
                           .title("Schornstein Cervejaria")
                           .snippet("Em Pomerode (SC)")
                           .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )

        googleMap?.addMarker(
            MarkerOptions().position(omasHausLocation)
                           .title("Omas Haus")
                           .snippet("Em Blumenau (SC)")
                           .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(schornsteinLocation, 10.5f))

        Toast.makeText(this, getUrl(schornsteinLocation, omasHausLocation), Toast.LENGTH_LONG).show()
    }

    private fun getUrl(from: LatLng, to: LatLng): String{
        return "https://maps.googleapis.com/maps/api/directions/json?origin=${from.latitude},${from.longitude}&destination=${to.latitude},${to.longitude}"
    }
}
