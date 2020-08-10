package com.app.swiperowexample.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.RecyclerView
import com.app.swiperowexample.DeleteCallback


class SwipeCallback(
    private val callback: DeleteCallback
) : ItemTouchHelper.Callback() {
    companion object {
        private const val BUTTON_WIDTH = 200
    }

    private var currentItemViewHolder: RecyclerView.ViewHolder? = null
    private var buttonRect: RectF? = null
    private var swipeBack: Boolean = false
    private var showedButtonState = SwipeButtonState.GONE

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.LEFT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = false
    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {

    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ACTION_STATE_SWIPE) {
            if (showedButtonState != SwipeButtonState.GONE) {
                var maxDx = dX
                if (showedButtonState == SwipeButtonState.RIGHT_VISIBLE) {
                    maxDx = Math.min(
                        dX,
                        -BUTTON_WIDTH.toFloat()
                    )
                }
                super.onChildDraw(c, recyclerView, viewHolder, maxDx, dY, actionState, isCurrentlyActive)
            } else {
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        if (showedButtonState == SwipeButtonState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        currentItemViewHolder = viewHolder
    }

    override fun convertToAbsoluteDirection(
        flags: Int,
        layoutDirection: Int
    ): Int {
        if (swipeBack) {
            swipeBack = showedButtonState != SwipeButtonState.GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { p0, p1 ->
            swipeBack = (p1?.action == MotionEvent.ACTION_CANCEL || p1?.action == MotionEvent.ACTION_UP)
            if (swipeBack) {
                if (dX < -BUTTON_WIDTH) {
                    showedButtonState = SwipeButtonState.RIGHT_VISIBLE
                }
            }

            if (showedButtonState != SwipeButtonState.GONE) {
                setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                setItemsClickable(recyclerView, false)
            }

            false
        }
    }

    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { p0, p1 ->
            if (p1?.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { p0, p1 ->
            if (p1?.action == MotionEvent.ACTION_UP) {
                super.onChildDraw(c, recyclerView, viewHolder, 0.0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { p0, p1 ->
                    false
                }
                setItemsClickable(recyclerView, true)
                swipeBack = false
                if (buttonRect?.contains(p1.x, p1.y) == true) {
                    callback(viewHolder.adapterPosition)
                }
                showedButtonState = SwipeButtonState.GONE
            }
            false
        }
    }

    private fun setItemsClickable(
        recyclerView: RecyclerView,
        isClickable: Boolean
    ) {
        for (index in 1 until recyclerView.childCount) {
            recyclerView.getChildAt(index).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding: Float = (BUTTON_WIDTH - 20).toFloat()
        val corners = 16f
        val itemView: View = viewHolder.itemView
        val p = Paint()
        val rightButton = RectF(
            itemView.right - buttonWidthWithoutPadding,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )
        p.color = Color.RED
        c.drawRoundRect(rightButton, corners, corners, p)
        drawText("DELETE", c, rightButton, p)
        buttonRect = null
        if (showedButtonState == SwipeButtonState.RIGHT_VISIBLE) {
            buttonRect = rightButton
        }
    }

    private fun drawText(
        text: String,
        c: Canvas,
        button: RectF,
        p: Paint
    ) {
        val textSize = 30f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize
        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    fun onDraw(c: Canvas?) {
        if (currentItemViewHolder != null) {
            drawButtons(c!!, currentItemViewHolder!!)
        }
    }
}