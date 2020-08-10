package com.app.swiperowexample.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.swiperowexample.R

class SwipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val firstNameText: TextView = view.findViewById(R.id.firstNameText)
    val lastNameText: TextView = view.findViewById(R.id.lastNameText)
}