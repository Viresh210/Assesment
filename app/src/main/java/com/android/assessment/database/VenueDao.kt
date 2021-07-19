package com.android.assessment.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.assessment.models.Venue

@Dao
interface VenueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(venues: List<Venue>)

    @Query("SELECT * FROM venue_data_table")
    fun getAll(): List<Venue>?

    @Query("DELETE FROM venue_data_table")
    fun deleteAll()

}