package com.ducpv.movie.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemMoviePopularBinding
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieApi
import com.ducpv.movie.shared.extension.hide
import com.ducpv.movie.shared.extension.loadImage

/**
 * Created by ducpv on 29/11/2022.
 */
class BookmarkAdapter(
    private val onItemMovieClickListener: (Movie) -> Unit,
) : ListAdapter<Movie, BookmarkAdapter.ItemVH>(BookmarkDiffCallBack) {
    object BookmarkDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH(
            ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemMovieClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemVH(
        private val binding: ItemMoviePopularBinding,
        private val onItemMovieClickListener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvVoting.text = String.format("%s/10 IMDb", movie.voteAverage)
            binding.tvOverview.text = movie.overview
            binding.ivPoster.loadImage(MovieApi.getPosterPath(movie.posterPath))
            binding.cbBookmark.hide()

            binding.root.setOnClickListener {
                onItemMovieClickListener.invoke(movie)
            }
        }
    }
}
