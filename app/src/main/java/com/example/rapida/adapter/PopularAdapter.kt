package com.example.rapida.adapter



import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rapida.helper.Constants
import com.example.rapida.models.Movie
import com.bumptech.glide.Glide
import com.example.rapida.MovieDetailActivity
import com.example.rapida.R
import com.example.rapida.databinding.TvShowLayoutAdapterBinding


class   PopularAdapter(): PagingDataAdapter<Movie, PopularAdapter.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tv_show_layout_adapter, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

        holder.itemView.setOnClickListener {

            val intentDetail = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intentDetail.putExtra("movie", item)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = TvShowLayoutAdapterBinding.bind(view)

        fun bind(movie: Movie) {
            //binding.textView.text = movie.title
            Glide.with(itemView.context).load(Constants.urlBaseImage + movie.posterPath).centerCrop().into(binding.imageView)
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.id == newItem.id
        }
    }
}















