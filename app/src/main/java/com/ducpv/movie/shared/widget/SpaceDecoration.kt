package com.ducpv.movie.shared.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ducpv on 30/11/2022.
 */
class SpaceDecoration @JvmOverloads constructor(
    private val spacingPx: Int,
    private val includeSpacingStart: Boolean = false,
    private val includeSpacingEnd: Boolean = false
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (spacingPx <= 0) {
            return
        }
        if (includeSpacingStart && parent.getChildLayoutPosition(view) < 1 || parent.getChildLayoutPosition(view) >= 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.top = spacingPx
            } else {
                outRect.left = spacingPx
            }
        }
        if (includeSpacingEnd && parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.bottom = spacingPx
            } else {
                outRect.right = spacingPx
            }
        }
    }

    private fun getTotalItemCount(parent: RecyclerView): Int {
        return parent.adapter?.itemCount ?: 0
    }

    private fun getOrientation(parent: RecyclerView): Int? {
        return if (parent.layoutManager is LinearLayoutManager) {
            (parent.layoutManager as? LinearLayoutManager)?.orientation
        } else {
            throw IllegalStateException("SpacingDecoration can only be used with a LinearLayoutManager.")
        }
    }
}

class GridSpaceDecoration(
    private val spanCount: Int,
    private val spacingVertical: Int,
    private val spacingHorizontal: Int,
    private val includeEdge: Boolean = false,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacingHorizontal - column * spacingHorizontal / spanCount
            outRect.right = (column + 1) * spacingHorizontal / spanCount
            if (position < spanCount) {
                outRect.top = spacingVertical
            }
            outRect.bottom = spacingVertical
        } else {
            outRect.left = column * spacingHorizontal / spanCount
            outRect.right = spacingHorizontal - (column + 1) * spacingHorizontal / spanCount
            if (position >= spanCount) {
                outRect.top = spacingVertical
            }
        }
    }
}
