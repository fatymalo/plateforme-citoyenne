package com.plateformecitoyenne.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Login : Screen("login")

    object Register : Screen("register")

    object Home : Screen("home")

    object Signaler : Screen("signaler")

    object Liste : Screen("liste")

    object Profile : Screen("profile")

    object Camera : Screen("camera")
    object Profil : Screen("profil")

    object Carte : Screen("carte")
}