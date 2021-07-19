package com.android.assessment.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue_data_table")
data class Venue(
    @PrimaryKey(autoGenerate = true) val uiD: Int,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    //val location: Location,
    @ColumnInfo(name = "adress") val adress: String,
    @ColumnInfo(name = "rating") val rating: String,
    @ColumnInfo(name = "description") val description: String
    //val contact: Contact
)

