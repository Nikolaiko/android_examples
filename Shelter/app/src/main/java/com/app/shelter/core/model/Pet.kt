package com.app.shelter.core.model

data class Pet(
    val id: Int,
    val name: String,
    val type: PetType,
    val owner: Int
)
