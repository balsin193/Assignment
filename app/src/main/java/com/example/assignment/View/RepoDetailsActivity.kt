package com.example.assignment.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.assignment.R
import com.example.assignment.databinding.ActivityRepoDetailsBinding

class RepoDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRepoDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details)
//        setContentView(R.layout.activity_repo_details)
        binding.backArrow.setOnClickListener { finish() }
        // Retrieve data from intent
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val link = intent.getStringExtra("link")
        val imageResource = intent.getIntExtra("image", 0)
        //set text and image
        binding.name.text=name
        binding.description.text=description
        binding.projectLink.text=link
        binding.imageView.setImageResource(imageResource)

        binding.projectLink.setOnClickListener {
            openWebView(link)
        }
    }
    private fun openWebView(url: String?) {
        if (!url.isNullOrEmpty()) {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Link not available", Toast.LENGTH_SHORT).show()
        }
    }
}