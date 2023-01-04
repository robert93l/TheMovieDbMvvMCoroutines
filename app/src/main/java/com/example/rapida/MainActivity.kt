package com.example.rapida

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapida.adapter.*
import com.example.rapida.databinding.ActivityMainBinding
import com.example.rapida.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var toprated: TopRatedAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter

    private lateinit var searchAdapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        loadDataPopular()
        loadDataNowPlaying()

        loadDatatopRated()
        loadDataUpcoming()

        loadSearch()
    }


    private fun loadSearch() {
        lifecycleScope.launch{
           viewModel.searchResults.collect{
                searchAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_character, menu)
        val item = menu.findItem(R.id.searchCharacterMenu)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchMovies(query)
                     loadSearch()
                }
              return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchMovies(newText)
                }
                return true
            }

        })
        return true
    }



    private fun loadDataPopular() {

        lifecycleScope.launch {
            viewModel.movieslistPopular.collect {

                Log.d("aaa", "load: $it")
                popularAdapter.submitData(it)
            }
        }
    }

    private fun loadDataNowPlaying() {

        lifecycleScope.launch {
            viewModel.movielistnowplaying.collect {

                Log.d("aaa", "load: $it")
                nowPlayingAdapter.submitData(it)
            }
        }
    }

    private fun loadDatatopRated() {

        lifecycleScope.launch {
            viewModel.movielisttopRated.collect {

                Log.d("aaa", "load: $it")
                toprated.submitData(it)
            }
        }
    }

    private fun loadDataUpcoming() {

        lifecycleScope.launch {
            viewModel.movielistUpcoming.collect {

                Log.d("aaa", "load: $it")
                upcomingAdapter.submitData(it)
            }
        }
    }



    private fun setUpRv() {
        popularAdapter = PopularAdapter()
        toprated = TopRatedAdapter()
        nowPlayingAdapter = NowPlayingAdapter()
        upcomingAdapter = UpcomingAdapter()

        searchAdapter = SearchAdapter()


        binding.recyclerView.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvEpisodes.apply {
            adapter = toprated
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }


        binding.rvRecentlyAdded.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvMoreAdded.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTopRatedAdded.apply {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvMostRecentAdded.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTest1.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTest2.apply {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTest3.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTest4.apply {
            adapter = toprated
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvTest5.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

    }
}