package com.app.shelter.petsList.model

import com.app.shelter.core.model.PetType

data class PetShortData(
    val id: Int,
    val name: String,
    val type: PetType
)