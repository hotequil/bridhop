package com.bridhop.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bridhop.AnimationsActivity
import com.bridhop.AudioActivity
import com.bridhop.CepActivity
import com.bridhop.LocationActivity
import com.bridhop.MapsActivity
import com.bridhop.R
import com.bridhop.RoutesActivity
import com.bridhop.VideoActivity
import com.bridhop.WebViewActivity

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val seeMapsButton = view.findViewById<Button>(R.id.see_maps)
        val locationButton = view.findViewById<Button>(R.id.location)
        val goRoutesButton = view.findViewById<Button>(R.id.go_routes)
        val webViewButton = view.findViewById<Button>(R.id.web_view)
        val audioButton = view.findViewById<Button>(R.id.audio)
        val videoButton = view.findViewById<Button>(R.id.video)
        val animationsButton = view.findViewById<Button>(R.id.animations)
        val cepButton = view.findViewById<Button>(R.id.cep)

        seeMapsButton.setOnClickListener{
             val intent = Intent(view.context, MapsActivity::class.java)

             startActivity(intent)
        }

        locationButton.setOnClickListener{
            val intent = Intent(view.context, LocationActivity::class.java)

            startActivity(intent)
        }

        goRoutesButton.setOnClickListener{
            val intent = Intent(view.context, RoutesActivity::class.java)

            startActivity(intent)
        }

        webViewButton.setOnClickListener{
            val intent = Intent(view.context, WebViewActivity::class.java)

            startActivity(intent)
        }

        audioButton.setOnClickListener{
            val intent = Intent(view.context, AudioActivity::class.java)

            startActivity(intent)
        }

        videoButton.setOnClickListener{
            val intent = Intent(view.context, VideoActivity::class.java)

            startActivity(intent)
        }

        animationsButton.setOnClickListener{
            val intent = Intent(view.context, AnimationsActivity::class.java)

            startActivity(intent)
        }

        cepButton.setOnClickListener{
            val intent = Intent(view.context, CepActivity::class.java)

            startActivity(intent)
        }

        return view
    }
}
