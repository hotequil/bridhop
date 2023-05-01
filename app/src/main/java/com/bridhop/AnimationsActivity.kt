package com.bridhop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class AnimationsActivity : AppCompatActivity() {
    private val animations = arrayOf(
        "Fade in",
        "Fade out",
        "Zoom in",
        "Zoom out",
        "Blink",
        "Rotate",
        "Move",
        "Slide up",
        "Slide down",
        "Bounce",
    )

    private val animationIds = intArrayOf(
        R.anim.fade_in,
        R.anim.fade_out,
        R.anim.zoom_in,
        R.anim.zoom_out,
        R.anim.blink,
        R.anim.rotate,
        R.anim.move,
        R.anim.slide_up,
        R.anim.slide_down,
        R.anim.bounce,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_animations)

        val text = findViewById<TextView>(R.id.text)
        val backButton = findViewById<Button>(R.id.back)
        val resetButton = findViewById<Button>(R.id.reset)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        resetButton.setOnClickListener{
            text.startAnimation(AnimationUtils.loadAnimation(this, R.anim.reset))
        }

        val listView = findViewById<ListView>(R.id.list_view)

        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animations)

        listView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val animation = AnimationUtils.loadAnimation(this, animationIds[position])

            text.startAnimation(animation)
        }
    }
}
