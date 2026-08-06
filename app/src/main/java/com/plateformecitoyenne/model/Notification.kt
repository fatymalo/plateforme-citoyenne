package com.plateformecitoyenne.model

data class Notification(

    val id: Long,

    val titre: String,

    val message: String,

    val lue: Boolean,

    val utilisateur: Utilisateur?

)