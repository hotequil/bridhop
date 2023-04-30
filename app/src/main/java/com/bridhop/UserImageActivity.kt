package com.bridhop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bridhop.ui.user.UserFragment
import com.bumptech.glide.Glide

class UserImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_image)

        val backButton = findViewById<Button>(R.id.back)

        backButton.setOnClickListener{
            val intent = Intent(this, UserFragment::class.java)

            startActivity(intent)
        }

        val photos = arrayOf("https://avatars.githubusercontent.com/u/46814712?v=4", "https://cdn.discordapp.com/avatars/372500190138990594/ce63622aa520b0d7e100457ea4c9cac8.webp?size=128")
        val githubImage = findViewById<ImageView>(R.id.github_image)
        val discordImage = findViewById<ImageView>(R.id.discord_image)

        photos.forEachIndexed{ index, photo ->
            if (index == 0) {
                Glide.with(this).load(photo).into(githubImage)
                githubImage.contentDescription = resources.getString(R.string.github_text)
            } else if(index == 1) {
                Glide.with(this).load(photo).into(discordImage)
                discordImage.contentDescription = resources.getString(R.string.discord_text)
            }
        }
    }
}
