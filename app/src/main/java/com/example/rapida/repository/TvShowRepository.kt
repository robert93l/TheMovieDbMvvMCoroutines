package com.example.rapida.repository


import com.example.rapida.api.ApiService
import com.example.rapida.helper.Constants
import javax.inject.Inject

class TvShowRepository
@Inject
constructor(private val apiService: ApiService) {

    suspend fun getPopular() = apiService.getPopularMovies(Constants.API_KEY,1)
    suspend fun getUpcoming() = apiService.getUpcomingMovies(Constants.API_KEY,1)
    suspend fun getNowPlaying() = apiService.getNowPlayingMovies(Constants.API_KEY,1)
    suspend fun getTopRated() = apiService.getTopRatedMovies(Constants.API_KEY,1)


}