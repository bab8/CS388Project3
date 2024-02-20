package com.example.flixster

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"
class DetailActivity: AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var rateTextView: TextView
    private lateinit var voteTextView: TextView
    private lateinit var releaseTextView: TextView
    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.movie_image)
        titleTextView = findViewById(R.id.movie_title)
        descTextView = findViewById(R.id.movie_description)
        rateTextView = findViewById(R.id.movie_rating)
        voteTextView = findViewById(R.id.movie_votes)
        releaseTextView = findViewById(R.id.movie_release)
        backButton = findViewById(R.id.back_button)

        val movie_title = intent.getStringExtra("title").toString()
        val movie_img = intent.getStringExtra("img").toString()
        val movie_desc = intent.getStringExtra("desc").toString()
        val movie_rating = intent.getStringExtra("rating").toString()
        val movie_votes = intent.getStringExtra("votes").toString()
        val movie_release = intent.getStringExtra("release").toString()

        titleTextView.text = movie_title
        descTextView.text = "Description: $movie_desc"
        rateTextView.text = "Rating: $movie_rating 10"
        voteTextView.text = "Total Votes:  $movie_votes"
        releaseTextView.text = "Release Date : $movie_release"
        val imageUrl = "https://image.tmdb.org/t/p/w500$movie_img"

        Glide.with(this)
            .load(imageUrl)
            .into(mediaImageView)

        backButton.setOnClickListener{
            finish()
        }
    }


}