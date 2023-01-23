package com.example.rapida.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rapida.api.ApiService
import com.example.rapida.models.Movie
import com.example.rapida.models.MoviesDatabase
import com.example.rapida.paging.*
import com.example.rapida.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository, private var query: String,private val moviesApiService: ApiService,
            private val moviesDatabase: MoviesDatabase) : ViewModel() {



        @OptIn(ExperimentalPagingApi::class)
        fun getPopularMovies(Typeofmovie: String): Flow<PagingData<Movie>> =
            Pager(
                 PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 10,
                    initialLoadSize = 20,
                ),
                pagingSourceFactory = {
                    moviesDatabase.getMoviesDao().getMovies()
                },
                remoteMediator = MoviesRemoteMediator(
                    moviesApiService,
                    moviesDatabase,
                    Typeofmovie
                )
            ).flow.cachedIn(viewModelScope)



   /*  val movieslistPopular = Pager(PagingConfig(pageSize = 20)) {
         MoviePagingSourcePopular()
     }.flow.cachedIn(viewModelScope)*/


    val movielistnowplaying = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceUpcoming("nowplayingmovies")
    }.flow.cachedIn(viewModelScope)

    /*val movielisttopRated = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceTop()
    }.flow.cachedIn(viewModelScope)*/

    val movielisttopRated = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceUpcoming("topratedmovies")
    }.flow.cachedIn(viewModelScope)

    val movielistUpcoming = Pager(PagingConfig(pageSize = 20)){
        MoviePagingSourceUpcoming("upcomingmovies")
    }.flow.cachedIn(viewModelScope)


   var searchResults: Flow<PagingData<Movie>> = emptyFlow()

    fun searchMovies(query: String) {
        this.query = query
        searchResults = Pager(PagingConfig(pageSize = 20)){
            MoviePagingSourceSearch(this.query)
        }.flow.cachedIn(viewModelScope)
    }

}













