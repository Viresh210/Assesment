package com.android.assessment.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.assessment.database.getDatabase
import com.android.assessment.models.Venue
import com.android.assessment.models.VenuesMainResponse
import com.android.assessment.network.ApiClient
import com.android.assessment.network.endpoints.VenuesEndpoint
import com.android.assessment.util.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnError {

            }.subscribe(

                { result ->

                    _data.value = result
                    insertData(result.response.venues)
                },

                { _ ->

                    // deal with error
                }

            )

        } catch (e: Exception) {

            _data.value = null
        }
    }

    private fun insertData(list: List<Venue>) {
        GlobalScope.launch {
            getDatabase(App.context).venueDao().insertAll(list)
        }
    }

}
