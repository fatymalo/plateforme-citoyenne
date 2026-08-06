package com.plateformecitoyenne.data.remote

import com.plateformecitoyenne.model.IncidentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IncidentApi {

    @POST("api/incidents")
    suspend fun createIncident(
        @Body incident: IncidentRequest
    ): Response<Unit>
}