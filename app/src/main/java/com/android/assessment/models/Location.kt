package com.android.assessment.models

data class Location(
    val address: String,
    val crossStreet: String,
    val lng: Double,
    val lat: Double
)