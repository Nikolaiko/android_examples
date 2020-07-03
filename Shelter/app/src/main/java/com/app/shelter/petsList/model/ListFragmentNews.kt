package com.app.shelter.petsList.model

data class ListFragmentNews(
    val errorMessage: String = "",
    val destination: ListFragmentDestinations = ListFragmentDestinations.NONE,
    val data: Any? = null
)
