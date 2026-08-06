package com.plateformecitoyenne.model

data class IncidentRequest(
    val titre: String,
    val description: String,
    val categorie: String,
    val priorite: String
)