package com.plateformecitoyenne.navigation


sealed class Screen(val route: String) {


    object Splash : Screen("splash")


    object Login : Screen("login")


    object Register : Screen("register")


    object Home : Screen("home")

    object Signaler : Screen("signaler")


    object Liste : Screen("liste")


    object Carte : Screen("carte")


    object Camera : Screen("camera")


    object Profil : Screen("profil")


    object EditProfil : Screen("edit_profil")


    object Notification : Screen("notifications")



    // ADMIN

    object AdminHome : Screen("admin_home")


    object AdminIncidents : Screen("admin_incidents")



    // MODIFICATION INCIDENT

    object ModifierIncident : Screen("modifier_incident/{id}") {


        fun createRoute(id: Long): String {

            return "modifier_incident/$id"

        }

    }

}