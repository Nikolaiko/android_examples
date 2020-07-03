package com.app.shelter.petsList.model

data class ListFragmentState(
    val isLoading: Boolean = false,
    val list: List<PetShortData> = emptyList()
)
