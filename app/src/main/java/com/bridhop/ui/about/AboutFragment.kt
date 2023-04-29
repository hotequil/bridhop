package com.bridhop.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bridhop.R

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        val moreAboutButton = view.findViewById<Button>(R.id.about_more_button)

        moreAboutButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)

            intent.setData(Uri.parse("https://fiap.com.br"))

            startActivity(intent)
        }

        return view
    }
}
