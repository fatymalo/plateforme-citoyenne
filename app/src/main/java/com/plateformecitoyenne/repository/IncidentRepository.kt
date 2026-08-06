package com.plateformecitoyenne.repository

import com.plateformecitoyenne.model.IncidentRequest
import com.plateformecitoyenne.network.ApiClient

class IncidentRepository {

    suspend fun envoyerIncident(
        titre: String,
        description: String,
        categorie: String,
        priorite: String
    ): Result<Unit> {

        return try {

            val response = ApiClient.apiService.createIncident(
                IncidentRequest(
                    titre = titre,
                    description = description,
                    categorie = categorie,
                    priorite = priorite
                )
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erreur serveur : ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}