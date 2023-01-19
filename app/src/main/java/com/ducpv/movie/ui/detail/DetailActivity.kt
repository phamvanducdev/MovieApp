package com.ducpv.movie.ui.detail

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.forEach
import com.ducpv.movie.R
import com.ducpv.movie.databinding.ActivityDetailBinding
import com.ducpv.movie.domain.service.MovieApi
import com.ducpv.movie.shared.base.BaseActivity
import com.ducpv.movie.shared.extension.dp
import com.ducpv.movie.shared.extension.loadImage
import com.ducpv.movie.shared.result.Result
import com.ducpv.movie.shared.result.asException
import com.ducpv.movie.shared.widget.SpaceDecoration
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 03/01/2023.
 */
@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {
    override val viewModel: DetailViewModel by viewModels()

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    private val castsAdapter by lazy {
        CastsAdapter {}
    }

    override fun initialize() {
        super.initialize()
        binding.rvCasts.adapter = castsAdapter
        binding.rvCasts.addItemDecoration(SpaceDecoration(8.dp))
    }

    override fun viewBinding() {
        super.viewBinding()
        binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val offsetChanged = (1F * verticalOffset) / (-1 * appBarLayout.totalScrollRange)
            val offsetTransition = 2F / 3F
            val iconColorRes = if (offsetChanged > offsetTransition) R.color.primary else R.color.white
            val colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(this, iconColorRes),
                BlendModeCompat.SRC_ATOP
            )
            binding.toolbar.navigationIcon?.colorFilter = colorFilter
            binding.toolbar.menu.forEach {
                it.icon?.colorFilter = colorFilter
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.movieDetailState.observe(this) {
            when (it) {
                is Result.Success -> {
                    val movie = it.data
                    binding.ivBackdrop.loadImage(MovieApi.getBackdropPath(movie.backdropPath))
                    binding.cbBookmark.isChecked = movie.isBookmarked
                    binding.tvTitle.text = movie.title
                    binding.tvDescription.text = movie.overview
                    binding.tvRating.text = String.format("%s/10 IMDb", movie.voteAverage)
                    binding.cgGenres.removeAllViews()
                    movie.genres.forEach { genre ->
                        binding.cgGenres.addView(
                            Chip(this).apply {
                                text = genre.name
                                setPadding(0, 0, 0, 0)
                                setTextColor(ContextCompat.getColor(this.context, R.color.secondary))
                                setTextAppearance(R.style.TextAppearanceMulish_LabelMediumNormal)
                                setChipBackgroundColorResource(R.color.gray)
                            }
                        )
                    }
                    castsAdapter.submitList(movie.casts)
                }
                is Result.Loading -> {
                }
                is Result.Error -> {
                    showSnackbar(it.asException())
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

    override fun events() {
        super.events()
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.cbBookmark.setOnCheckedChangeListener { compoundButton, checked ->
            if (compoundButton.isPressed) {
                viewModel.onClickBookmark(checked)
            }
        }
    }
}
