package com.bridhop

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val backButton = findViewById<Button>(R.id.back)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.maps) as SupportMapFragment

        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val schornsteinPomerode = LatLng(-26.7384462, -49.1736941)

        googleMap.addMarker(
            MarkerOptions().position(schornsteinPomerode)
                           .title("Schornstein Cervejaria (Pomerode - SC)")
                           .snippet("Uma das melhores cervejarias do Brasil.")
                           .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        )

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(schornsteinPomerode, 12.5f))

        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                Log.i("Marker", marker.toString())

                return null
            }

            override fun getInfoContents(marker: Marker): View {
                val info = LinearLayout(applicationContext)

                info.orientation = LinearLayout.VERTICAL

                val title = TextView(applicationContext)

                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.START
                title.setTypeface(null, Typeface.BOLD)
                title.text = marker.title

                val snippet = TextView(applicationContext)

                snippet.setTextColor(Color.GRAY)
                snippet.text = marker.snippet

                info.addView(title)
                info.addView(snippet)

                return info
            }
        })
    }
}
