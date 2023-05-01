package com.bridhop

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)

        val backButton = findViewById<Button>(R.id.back)

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        val webView = findViewById<WebView>(R.id.web_view)

        webView.webViewClient = object : WebViewClient(){
            @SuppressLint("SetJavaScriptEnabled")
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.settings?.javaScriptEnabled = true
                view?.loadUrl(request?.url.toString())

                return true
            }
        }

        webView.loadUrl("https://hotequil.tech")
    }
}
