package com.ducpv.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemHomeHeaderPopularBinding
import com.ducpv.movie.databinding.ItemHomeLoadingBinding
import com.ducpv.movie.databinding.ItemHomeMoviesShowingsBinding
import com.ducpv.movie.databinding.ItemMoviePopularBinding
import com.ducpv.movie.domain.model.Movie
import com.ducpv.movie.domain.service.MovieApi
import com.ducpv.movie.shared.extension.dp
import com.ducpv.movie.shared.extension.loadImage
import com.ducpv.movie.shared.widget.SpaceDecoration

/**
 * Created by ducpv on 29/11/2022.
 */
class HomeAdapter(
    private val onItemMovieClickListener: (Movie) -> Unit,
    private val onBookmarkMovieClickListener: (Movie) -> Unit,
    private val onViewMoreShowingClickListener: () -> Unit,
    private val onViewMorePopularClickListener: () -> Unit,
) : ListAdapter<ItemHomeUi, RecyclerView.ViewHolder>(HomeDiffCallBack) {
    object HomeDiffCallBack : DiffUtil.ItemCallback<ItemHomeUi>() {
        override fun areItemsTheSame(oldItem: ItemHomeUi, newItem: ItemHomeUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemHomeUi, newItem: ItemHomeUi): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemHomeUiType.SHOWINGS.type -> {
                ItemHomeShowingsVH(
                    ItemHomeMoviesShowingsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemMovieClickListener,
                    onBookmarkMovieClickListener,
                    onViewMoreShowingClickListener
                )
            }
            ItemHomeUiType.HEADER_POPULARS.type -> {
                ItemHomeHeaderPopularVH(
                    ItemHomeHeaderPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onViewMorePopularClickListener
                )
            }
            ItemHomeUiType.MOVIE_POPULAR.type -> {
                ItemHomeMoviePopularVH(
                    ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemMovieClickListener,
                    onBookmarkMovieClickListener
                )
            }
            else -> {
                ItemHomeLoadingVH(
                    ItemHomeLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemHomeShowingsVH -> {
                val movies = (getItem(position) as? ItemHomeUi.ItemShowings)?.movies.orEmpty()
                holder.bind(movies)
            }
            is ItemHomeMoviePopularVH -> {
                val movie = (getItem(position) as? ItemHomeUi.ItemMoviePopular)?.movie
                if (movie != null) holder.bind(movie)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.type
    }

    class ItemHomeLoadingVH(
        private val binding: ItemHomeLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ItemHomeHeaderPopularVH(
        private val binding: ItemHomeHeaderPopularBinding,
        private val onViewMorePopularClickListener: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvSeeMore.setOnClickListener {
                onViewMorePopularClickListener.invoke()
            }
        }
    }

    class ItemHomeShowingsVH(
        private val binding: ItemHomeMoviesShowingsBinding,
        private val onItemMovieClickListener: (Movie) -> Unit,
        private val onBookmarkMovieClickListener: (Movie) -> Unit,
        private val onViewMoreShowingsClickListener: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val moviesShowingAdapter: MoviesShowingAdapter by lazy {
            MoviesShowingAdapter(onItemMovieClickListener, onBookmarkMovieClickListener)
        }

        init {
            binding.rvMovies.adapter = moviesShowingAdapter
            binding.rvMovies.addItemDecoration(SpaceDecoration(8.dp))
            binding.tvSeeMore.setOnClickListener {
                onViewMoreShowingsClickListener.invoke()
            }
        }

        fun bind(movies: List<Movie>) {
            moviesShowingAdapter.submitList(movies)
        }
    }

    class ItemHomeMoviePopularVH(
        private val binding: ItemMoviePopularBinding,
        private val onItemMovieClickListener: (Movie) -> Unit,
        private val onBookmarkMovieClickListener: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvVoting.text = String.format("%s/10 IMDb", movie.voteAverage)
            binding.tvOverview.text = movie.overview
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

enum class ItemHomeUiType(val type: Int) {
    LOADING(0),
    SHOWINGS(1),
    HEADER_POPULARS(2),
    MOVIE_POPULAR(3)
}

sealed class ItemHomeUi(
    val id: String,
    val type: ItemHomeUiType
) {
    class ItemLoading : ItemHomeUi(
        id = ItemHomeUiType.LOADING.name,
        type = ItemHomeUiType.LOADING
    )

    class ItemShowings(val movies: List<Movie>) : ItemHomeUi(
        id = ItemHomeUiType.SHOWINGS.name,
        type = ItemHomeUiType.SHOWINGS
    )

    class ItemHeaderPopulars : ItemHomeUi(
        id = ItemHomeUiType.HEADER_POPULARS.name,
        type = ItemHomeUiType.HEADER_POPULARS
    )

    class ItemMoviePopular(val movie: Movie) : ItemHomeUi(
        id = movie.id,
        type = ItemHomeUiType.MOVIE_POPULAR
    )
}
