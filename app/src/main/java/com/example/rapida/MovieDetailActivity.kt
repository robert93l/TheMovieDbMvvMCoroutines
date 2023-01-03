package com.example.rapida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rapida.databinding.ActivityMovieDetailBinding
import com.example.rapida.helper.Constants
import com.example.rapida.models.Movie



class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movie = intent.getSerializableExtra("movie") as Movie


        binding.collapsingToolbarMaterial.title = movie.title
        Glide.with(this).load(Constants.urlBaseImage + movie.backdropPath)
            .centerCrop().into(binding.backdropImv)
        binding.OverviewTv.text = movie.overview
        binding.RelaseDateTv.text = movie.releaseDate

    }
}