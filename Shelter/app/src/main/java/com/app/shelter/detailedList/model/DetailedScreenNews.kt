package com.app.shelter.detailedList.model

data class DetailedScreenNews(
    val errorMessage: String = "",
    val destination: DetailedScreenDestinations = DetailedScreenDestinations.NOT_SET,
    val data: Any? = null
)
