package com.plateformecitoyenne.network

import com.plateformecitoyenne.model.Incident
import com.plateformecitoyenne.model.IncidentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/incidents")
    suspend fun getIncidents(): List<Incident>

    @POST("api/incidents")
    suspend fun createIncident(
        @Body incident: IncidentRequest
    ): Response<Unit>
}



