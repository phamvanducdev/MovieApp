package com.ducpv.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemMovieNowShowingBinding
import com.ducpv.movie.shared.extension.loadImage
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieApi

/**
 * Created by pvduc9773 on 29/11/2022.
 */
class NowShowingsAdapter(
    private val onItemMovieClickListener: (Movie) -> Unit
) : ListAdapter<Movie, NowShowingsAdapter.ItemMovieNowShowingVH>(NowShowingsDiffCallBack) {
    object NowShowingsDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieNowShowingVH {
        val width = ((parent.measuredWidth - 16 * 3) / 2.5).toInt()
        return ItemMovieNowShowingVH(
            ItemMovieNowShowingBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemMovieClickListener
        ).apply {
            itemView.layoutParams = ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onBindViewHolder(holder: ItemMovieNowShowingVH, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemMovieNowShowingVH(
        private val binding: ItemMovieNowShowingBinding,
        private val onItemMovieClickListener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.ivPoster.loadImage(MovieApi.getPosterPath(movie.posterPath))

            binding.root.setOnClickListener {
                onItemMovieClickListener.invoke(movie)
            }
        }
    }
}
