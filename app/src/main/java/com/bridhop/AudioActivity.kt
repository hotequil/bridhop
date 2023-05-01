package com.bridhop

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AudioActivity : AppCompatActivity() {
    private var audio: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audio)

        val playButton = findViewById<Button>(R.id.play)
        audio = MediaPlayer.create(this, R.raw.ringtone)

        playButton.setOnClickListener{
            audio?.start()
        }
    }
}
