package com.bridhop.ui.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bridhop.R
import com.bridhop.UserImageActivity

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val getUserImageButton = view.findViewById<Button>(R.id.get_user_image_button)

        getUserImageButton.setOnClickListener{
            val intent = Intent(view.context, UserImageActivity::class.java)

            startActivity(intent)
        }

        return view
    }
}
