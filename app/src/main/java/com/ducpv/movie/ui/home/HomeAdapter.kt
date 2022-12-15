package com.ducpv.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemHomeHeaderPopularBinding
import com.ducpv.movie.databinding.ItemHomeLoadingBinding
import com.ducpv.movie.databinding.ItemHomeNowShowingsBinding
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
    private val onViewMoreNowShowingClickListener: () -> Unit,
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
            ItemHomeUiType.NOW_SHOWINGS.type -> {
                ItemHomeNowShowingsVH(
                    ItemHomeNowShowingsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onItemMovieClickListener,
                    onViewMoreNowShowingClickListener
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
                    onItemMovieClickListener
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
            is ItemHomeNowShowingsVH -> {
                val movies = (getItem(position) as? ItemHomeUi.ItemNowShowings)?.movies.orEmpty()
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

    class ItemHomeNowShowingsVH(
        private val binding: ItemHomeNowShowingsBinding,
        private val onItemMovieClickListener: (Movie) -> Unit,
        private val onViewMorePopularClickListener: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val nowShowingsAdapter: NowShowingsAdapter by lazy {
            NowShowingsAdapter(onItemMovieClickListener)
        }

        init {
            binding.rvMovies.adapter = nowShowingsAdapter
            binding.rvMovies.addItemDecoration(SpaceDecoration(8.dp))
            binding.tvSeeMore.setOnClickListener {
                onViewMorePopularClickListener.invoke()
            }
        }

        fun bind(movies: List<Movie>) {
            nowShowingsAdapter.submitList(movies)
        }
    }

    class ItemHomeMoviePopularVH(
        private val binding: ItemMoviePopularBinding,
        private val onItemMovieClickListener: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvVoting.text = String.format("%s/10 IMDb", movie.voteAverage)
            binding.tvOverview.text = movie.overview
            binding.ivPoster.loadImage(MovieApi.getPosterPath(movie.posterPath))

            binding.root.setOnClickListener {
                onItemMovieClickListener.invoke(movie)
            }
        }
    }
}

enum class ItemHomeUiType(val type: Int) {
    LOADING(0),
    NOW_SHOWINGS(1),
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

    class ItemNowShowings(val movies: List<Movie>) : ItemHomeUi(
        id = ItemHomeUiType.NOW_SHOWINGS.name,
        type = ItemHomeUiType.NOW_SHOWINGS
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
