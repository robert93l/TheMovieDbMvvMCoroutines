package com.example.rapida.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rapida.paging.*
import com.example.rapida.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository) : ViewModel() {


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

/*    val movieSearch = Pager (PagingConfig(pageSize = 20)){
        MoviePagingSourceSearch(query = "")
    }.flow.cachedIn(viewModelScope)*/










}













