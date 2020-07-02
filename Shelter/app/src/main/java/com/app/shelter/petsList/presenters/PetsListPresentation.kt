package com.app.shelter.petsList.presenters

import com.app.shelter.petsList.views.PetsListView

interface PetsListPresentation {
    fun bind(presenterView: PetsListView)
    fun unbind()
    fun refresh()

    fun petRowClicked(rowId: Int)
}