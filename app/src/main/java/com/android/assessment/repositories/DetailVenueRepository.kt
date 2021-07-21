package com.android.assessment.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.assessment.models.DetailVenueMainResponse
import com.android.assessment.network.ApiClient
import com.android.assessment.network.endpoints.DetailVenueEndpoint

class DetailVenueRepository {

    private val _data: MutableLiveData<DetailVenueMainResponse?> = MutableLiveData(null)
    val data: LiveData<DetailVenueMainResponse?> get() = _data

    suspend fun fetch(venuesID: String, date: String) {
        val retrofit = ApiClient()
        val api = retrofit.retro.create(DetailVenueEndpoint::class.java)

        try {
            val response = api.get(
                venueID = venuesID,
                date = date
            )
            _data.value = response
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            _data.value = null
        }
    }

}