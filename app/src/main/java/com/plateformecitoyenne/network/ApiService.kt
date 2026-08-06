package com.plateformecitoyenne.network

import com.plateformecitoyenne.model.Incident
import com.plateformecitoyenne.model.IncidentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import com.plateformecitoyenne.model.LoginRequest
import com.plateformecitoyenne.model.Utilisateur
import com.plateformecitoyenne.model.Notification


interface ApiService {


    // ==========================
    // CITOYEN
    // ==========================


    // Récupérer tous les incidents
    @GET("api/incidents")
    suspend fun getIncidents(): List<Incident>



    // Créer un nouvel incident
    @POST("api/incidents")
    suspend fun createIncident(
        @Body incident: IncidentRequest
    ): Response<Unit>



    // Ajouter un vote / soutien
    @POST("api/votes/{incidentId}")
    suspend fun voterIncident(
        @Path("incidentId") incidentId: Long,
        @Query("utilisateurId") utilisateurId: Long
    ): String




    // ==========================
    // ADMIN
    // ==========================


    // Voir tous les incidents côté admin
    @GET("api/admin/incidents")
    suspend fun getAdminIncidents(): List<Incident>



    // Modifier le statut d'un incident
    @PUT("api/admin/incident/{id}/statut")
    suspend fun modifierStatut(
        @Path("id") id: Long,
        @Query("statut") statut: String
    ): Incident

    @PUT("api/admin/incident/{id}/priorite")
    suspend fun modifierPriorite(
        @Path("id") id: Long,
        @Query("priorite") priorite: String
    ): Incident

    @POST("api/auth/login")
    suspend fun login(
        @Body utilisateur: LoginRequest
    ): Utilisateur

    // ==========================
// NOTIFICATIONS
// ==========================

    @GET("api/notifications/{utilisateurId}")
    suspend fun getNotifications(
        @Path("utilisateurId") utilisateurId: Long
    ): List<Notification>



}