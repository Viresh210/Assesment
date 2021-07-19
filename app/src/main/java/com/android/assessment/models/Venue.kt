package com.android.assessment.models

data class Venue(
    val id: String,
    val name: String,
    val location: Location,
    val address: String,
    val rating: String,
    val description: String,
    val contact: Contact
)

