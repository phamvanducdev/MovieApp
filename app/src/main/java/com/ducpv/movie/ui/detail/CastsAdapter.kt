package com.ducpv.movie.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ducpv.movie.databinding.ItemCastBinding
import com.ducpv.movie.domain.model.Cast
import com.ducpv.movie.domain.service.MovieApi
import com.ducpv.movie.shared.extension.loadImage

/**
 * Created by ducpv on 29/11/2022.
 */
class CastsAdapter(
    private val onItemCastClickListener: (Cast) -> Unit
) : ListAdapter<Cast, CastsAdapter.ItemVH>(CastDiffCallBack) {
    object CastDiffCallBack : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val width = ((parent.measuredWidth - 16 * 5).toFloat() / 4).toInt()
        return ItemVH(
            ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemCastClickListener
        ).apply {
            itemView.layoutParams = ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemVH(
        private val binding: ItemCastBinding,
        private val onItemCastClickListener: (Cast) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.ivProfile.loadImage(MovieApi.getPosterPath(cast.profilePath))
            binding.tvName.text = cast.originalName
            binding.root.setOnClickListener {
                onItemCastClickListener.invoke(cast)
            }
        }
    }
}
