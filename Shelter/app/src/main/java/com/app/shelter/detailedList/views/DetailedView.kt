package com.app.shelter.detailedList.views

import com.app.shelter.core.model.Pet
import com.app.shelter.core.model.PetOwner
import com.app.shelter.detailedList.model.DetailedScreenDestinations

interface DetailedView {
    fun setLoadingIndicator(isLoading: Boolean)
    fun navigateTo(destination: DetailedScreenDestinations, data: Any?)
    fun displayErrorMessage(message: String)
    fun setPetAndOwnerData(pet: Pet, owner: PetOwner)
}