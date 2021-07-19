package com.android.assessment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.assessment.models.Venue

@Database(entities = [Venue::class], version = 1, exportSchema = false)
abstract class VenueDatabaseDatabase : RoomDatabase() {
    abstract fun venueDao(): VenueDao
}

private lateinit var INSTANCE: VenueDatabaseDatabase

fun getDatabase(context: Context): VenueDatabaseDatabase {

    synchronized(VenueDatabaseDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VenueDatabaseDatabase::class.java,
                "database"
            ).build()
        }
    }
    return INSTANCE

}