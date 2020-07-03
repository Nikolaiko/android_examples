package com.app.shelter.petsList.views

import com.app.shelter.petsList.model.ListFragmentDestinations
import com.app.shelter.petsList.model.PetShortData

interface PetsListView {
    fun setLoadingIndicator(isLoading: Boolean)
    fun updatePetsList(pets: List<PetShortData>)
    fun navigateTo(destination: ListFragmentDestinations, data: Any?)
    fun displayErrorMessage(message: String)
}
