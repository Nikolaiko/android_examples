package com.app.shelter.petsList.views.elements

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_pet_short_data.view.*

class PetsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.petNameText
    val typeTextView: TextView = itemView.petTypeText
    val parentLayout: ConstraintLayout = itemView.parentLayout
}
