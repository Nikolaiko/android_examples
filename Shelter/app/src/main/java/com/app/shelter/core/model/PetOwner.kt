package com.app.shelter.core.model

data class PetOwner(
    val id: Int,
    val name: String,
    val lastName: String,
    val pets: List<Pet>
)