package com.app.shelter.detailedList.model

import com.app.shelter.core.model.Pet
import com.app.shelter.core.model.PetOwner

data class DetailedScreenState(
    val isLoading: Boolean = false,
    val petData: Pet? = null,
    val ownerData: PetOwner? = null
)