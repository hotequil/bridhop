package com.bridhop

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AudioActivity : AppCompatActivity() {
    private var audio: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audio)

        val backButton = findViewById<Button>(R.id.back)
        val playButton = findViewById<Button>(R.id.play)
        audio = MediaPlayer.create(this, R.raw.ringtone)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        playButton.setOnClickListener{
            audio?.start()
        }
    }
}
