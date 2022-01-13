package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Article : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val image = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")
        val con = intent.getStringExtra("content")

        val textView = findViewById<TextView>(R.id.textView3)
        val content = findViewById<TextView>(R.id.content)
        val imageView = findViewById<ImageView>(R.id.imageView)

        textView.text = title
        content.text = con
        Glide.with(this).load(image).into(imageView)

    }
}