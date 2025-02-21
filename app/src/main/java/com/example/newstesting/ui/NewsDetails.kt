package com.example.newstesting.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.newstesting.R

class NewsDetails : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var newsImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_details)

        titleTextView = findViewById(R.id.tvNewsTitle)
        descriptionTextView = findViewById(R.id.tvNewsDescription)
        newsImageView = findViewById(R.id.ivNewsImage)

        val title = intent.getStringExtra("news_title")
        val description = intent.getStringExtra("news_description")
        val imageUrl = intent.getStringExtra("news_image")

        // Set data
        titleTextView.text = title
        descriptionTextView.text = description
        Glide.with(this).load(imageUrl).into(newsImageView)

    }
}