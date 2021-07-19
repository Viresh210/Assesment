package com.android.assessment.network.endpoints

import com.android.assessment.models.VenuesMainResponse
import com.android.assessment.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesEndpoint {

    @GET("v2/venues/search")
   suspend  fun get(
        @Query("near") city: String,
        @Query("limit") limit: String = Constants.limit,
        @Query("radius") radius: String = Constants.radius,
        @Query("client_id") id: String = Constants.clientId,
        @Query("client_secret") secret: String = Constants.clientSecret,
        @Query("v") date: String
    ): Observable<VenuesMainResponse>
}


