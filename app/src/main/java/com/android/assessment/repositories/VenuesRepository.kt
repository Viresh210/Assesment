package com.android.assessment.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.assessment.models.VenuesMainResponse
import com.android.assessment.network.ApiClient
import com.android.assessment.network.endpoints.VenuesEndpoint

class VenuesRepository() {

    private val _data: MutableLiveData<VenuesMainResponse?> = MutableLiveData(null)
    val data: LiveData<VenuesMainResponse?> get() = _data

    suspend fun fetch(city: String, date: String) {

        val retrofit = ApiClient()
        val api = retrofit.retro.create(VenuesEndpoint::class.java)

        try {
            val response = api.get(
                city = city,
                date = date
            )
            _data.value = response
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            _data.value = null
        }
    }

}
