package com.android.assessment.network.endpoints

import com.android.assessment.models.VenuesMainResponse
import com.android.assessment.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface VenuesEndpoint {

    @GET("v2/venues/search")
    suspend fun get(
        @Query("near") city: String,
        @Query("limit") limit: String = Constants.LIMIT,
        @Query("radius") radius: String = Constants.RADIUS,
        @Query("client_id") id: String = Constants.CLIENT_ID,
        @Query("client_secret") secret: String = Constants.CLIENT_SECRET,
        @Query("v") date: String
    ): VenuesMainResponse

}


