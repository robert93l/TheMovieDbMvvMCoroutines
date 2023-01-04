package com.example.rapida.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rapida.models.Movie
import com.example.rapida.paging.*
import com.example.rapida.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository, private var query: String) : ViewModel() {


    val movieslistPopular = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSourcePopular()
    }.flow.cachedIn(viewModelScope)

    val movielistnowplaying = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSource()
    }.flow.cachedIn(viewModelScope)

    val movielisttopRated = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceTop()
    }.flow.cachedIn(viewModelScope)

    val movielistUpcoming = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceUpcoming()
    }.flow.cachedIn(viewModelScope)


   var searchResults: Flow<PagingData<Movie>> = emptyFlow()

    fun searchMovies(query: String) {
        this.query = query
        searchResults = Pager(PagingConfig(pageSize = 20)){
            MoviePagingSourceSearch(this.query)
        }.flow.cachedIn(viewModelScope)
    }


}













