package com.example.assignment.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import com.example.assignment.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val webView: WebView = findViewById(R.id.webView)
        val back_arrow: ImageButton = findViewById(R.id.back_arrow)

        val url = intent.getStringExtra("url")

        webView.webViewClient = WebViewClient()

        // Enable JavaScript if needed
        webView.settings.javaScriptEnabled = true

        webView.loadUrl(url ?: "")

        back_arrow.setOnClickListener { finish() }
        // Optional: handle back button press
        webView.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack()
                true
            } else {
                false
            }
        }
    }
}