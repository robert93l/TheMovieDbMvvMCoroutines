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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


@HiltViewModel
class TvShowViewModel
@Inject
constructor(private val repository: TvShowRepository, private var query: String,private val moviesApiService: ApiService,
            private val moviesDatabase: MoviesDatabase,) : ViewModel() {



        @OptIn(ExperimentalPagingApi::class)
        fun getPopularMovies(): Flow<PagingData<Movie>> =
            Pager(
                 PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 5,
                    initialLoadSize = 20,
                ),
                pagingSourceFactory = {
                    moviesDatabase.getMoviesDao().getMovies()
                },
                remoteMediator = MoviesRemoteMediator(
                    moviesApiService,
                    moviesDatabase,
                )
            ).flow.cachedIn(viewModelScope)



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













