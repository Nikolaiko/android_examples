package com.app.swiperowexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.swiperowexample.R
import com.app.swiperowexample.data.User

class SwipeAdapter : RecyclerView.Adapter<SwipeViewHolder>() {

    var usersList: List<User> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = usersList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.row_table_layout, parent, false)
        return SwipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.firstNameText.text = usersList[position].firstName
        holder.lastNameText.text = usersList[position].lastName
    }
}