package com.example.rapida.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rapida.api.ApiRetrofit
import com.example.rapida.helper.Constants
import com.example.rapida.models.Movie
import retrofit2.HttpException
import java.io.IOException

private const val MOVIES_STARTING_PAGE_INDEX = 1

class MoviePagingSourceSearch(private val query:String): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val currentPageList = params.key ?: MOVIES_STARTING_PAGE_INDEX
            val response = ApiRetrofit.movieService.searchMovies(
                Constants.API_KEY,
                currentPageList,
                query = query
            )
            val responseList = mutableListOf<Movie>()

            if(response.isSuccessful){
                val data = response.body()?.movies ?: emptyList()
                responseList.addAll(data)
            }

            LoadResult.Page(
                data = responseList,
                prevKey = if (currentPageList == MOVIES_STARTING_PAGE_INDEX) null else currentPageList - 1,
                nextKey = currentPageList.plus(1)
            )
        }

        catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}