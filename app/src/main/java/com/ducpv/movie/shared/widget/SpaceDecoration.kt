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
    private val enableStartSpacing: Boolean = false,
    private val enableEndSpacing: Boolean = false
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
        if (enableStartSpacing && parent.getChildLayoutPosition(view) < 1 || parent.getChildLayoutPosition(view) >= 1) {
            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect.top = spacingPx
            } else {
                outRect.left = spacingPx
            }
        }
        if (enableEndSpacing && parent.getChildAdapterPosition(view) == getTotalItemCount(parent) - 1) {
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
