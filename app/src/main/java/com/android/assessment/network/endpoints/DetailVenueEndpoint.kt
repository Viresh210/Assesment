package com.android.assessment.network.endpoints

import com.android.assessment.models.DetailVenueMainResponse
import com.android.assessment.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailVenueEndpoint {

    @GET("v2/venues/{VENUEID}")
    suspend fun get(
        @Path("VENUEID") venueID: String,
        @Query("client_id") id: String = Constants.clientId,
        @Query("client_secret") secret: String = Constants.clientSecret,
        @Query("v") date: String
    ): DetailVenueMainResponse

}


