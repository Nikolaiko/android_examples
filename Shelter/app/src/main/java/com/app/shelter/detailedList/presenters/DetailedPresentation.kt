package com.app.shelter.detailedList.presenters

import com.app.shelter.detailedList.views.DetailedView

interface DetailedPresentation {
    fun bind(presenterView: DetailedView)
    fun unbind()

    fun loadPetData(id: Int)
    fun backButtonTap()
}