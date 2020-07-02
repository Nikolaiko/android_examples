package com.app.shelter.petsList.views.elements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.shelter.R
import com.app.shelter.petsList.PetRowClickCallback
import com.app.shelter.petsList.model.PetShortData

class PetsListAdapter(
    private val rowClickCallback: PetRowClickCallback
) : RecyclerView.Adapter<PetsListViewHolder>() {
    private var pets: List<PetShortData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsListViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.row_pet_short_data, parent, false)
        return  PetsListViewHolder(rowView)
    }

    override fun getItemCount(): Int = pets.size

    override fun onBindViewHolder(holder: PetsListViewHolder, position: Int) {
        holder.typeTextView.text = pets[position].type.name
        holder.nameTextView.text = pets[position].name
        holder.parentLayout.setOnClickListener {
            rowClickCallback(pets[position].id)
        }
    }

    fun updateList(newList: List<PetShortData>) {
        pets = newList
    }
}