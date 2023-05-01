package com.bridhop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video)

        val backButton = findViewById<Button>(R.id.back)
        val videoView = findViewById<VideoView>(R.id.video)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        videoView.setVideoURI(Uri.parse("https://www.w3schools.com/html/mov_bbb.mp4"))
        videoView.requestFocus()
        videoView.start()
    }
}
