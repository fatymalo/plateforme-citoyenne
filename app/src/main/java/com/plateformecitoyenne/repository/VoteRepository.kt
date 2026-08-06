package com.plateformecitoyenne.repository

import com.plateformecitoyenne.network.RetrofitClient


class VoteRepository {


    suspend fun voter(
        incidentId: Long,
        utilisateurId: Long
    ): String {

        return RetrofitClient.apiService
            .voterIncident(
                incidentId,
                utilisateurId
            )
    }
}