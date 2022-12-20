package com.ducpv.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemMovieShowingBinding
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieApi
import com.ducpv.movie.shared.extension.loadImage

/**
 * Created by ducpv on 29/11/2022.
 */
class MoviesShowingAdapter(
    private val onItemMovieClickListener: (Movie) -> Unit,
    private val onBookmarkMovieClickListener: (Movie) -> Unit,
) : ListAdapter<Movie, MoviesShowingAdapter.ItemMovieShowingVH>(MovieShowingDiffCallBack) {
    object MovieShowingDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieShowingVH {
        val width = ((parent.measuredWidth - 16 * 3) / 2.5).toInt()
        return ItemMovieShowingVH(
            ItemMovieShowingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemMovieClickListener,
            onBookmarkMovieClickListener
        ).apply {
            itemView.layoutParams = ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onBindViewHolder(holder: ItemMovieShowingVH, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemMovieShowingVH(
        private val binding: ItemMovieShowingBinding,
        private val onItemMovieClickListener: (Movie) -> Unit,
        private val onBookmarkMovieClickListener: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.ivPoster.loadImage(MovieApi.getPosterPath(movie.posterPath))
            binding.cbBookmark.isChecked = movie.isBookmarked
            binding.cbBookmark.setOnCheckedChangeListener { compoundButton, checked ->
                if (compoundButton.isPressed) {
                    movie.isBookmarked = checked
                    onBookmarkMovieClickListener.invoke(movie)
                }
            }
            binding.root.setOnClickListener {
                onItemMovieClickListener.invoke(movie)
            }
        }
    }
}
