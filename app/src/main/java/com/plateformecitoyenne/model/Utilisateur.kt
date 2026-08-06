package com.plateformecitoyenne.model

data class Utilisateur(

    val id: Long,

    val nom: String,

    val prenom: String?,

    val email: String,

    val role: String

)