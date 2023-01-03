package com.example.rapida

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rapida.adapter.NowPlayingAdapter
import com.example.rapida.adapter.PopularAdapter
import com.example.rapida.adapter.TopRatedAdapter
import com.example.rapida.adapter.UpcomingAdapter
import com.example.rapida.databinding.ActivityMainBinding
import com.example.rapida.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var toprated: TopRatedAdapter
    private lateinit var nowPlayingAdapter: NowPlayingAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setUpRv()
        loadDataPopular()
        loadDataNowPlaying()
        loadDatatopRated()
        loadDataUpcoming()
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


        binding.recyclerView.apply {
            adapter = popularAdapter
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