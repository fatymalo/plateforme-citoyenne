package com.plateformecitoyenne.model

data class Incident(
    val id: Long? = null,
    val titre: String,
    val description: String,
    val photo: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val adresse: String? = null,
    val priorite: String,
    val statut: String,
    val nombreVotes: Int = 0
)