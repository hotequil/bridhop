package com.bridhop.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bridhop.MapsActivity
import com.bridhop.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val seeMapsButton = view.findViewById<Button>(R.id.see_maps)

        seeMapsButton.setOnClickListener{
             val intent = Intent(view.context, MapsActivity::class.java)

             startActivity(intent)
        }

        return view
    }
}
