package com.plateformecitoyenne.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plateformecitoyenne.presentation.auth.ConnexionScreen
import com.plateformecitoyenne.presentation.auth.InscriptionScreen
import com.plateformecitoyenne.presentation.camera.CameraScreen
import com.plateformecitoyenne.presentation.history.ListeIncidentsScreen
import com.plateformecitoyenne.presentation.home.AccueilScreen
import com.plateformecitoyenne.presentation.incident.SignalerIncidentScreen
import com.plateformecitoyenne.presentation.map.CarteIncidentsScreen
import com.plateformecitoyenne.presentation.splash.SplashScreen
import com.plateformecitoyenne.profil.ProfilScreen


@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {


        composable(Screen.Splash.route) {

            SplashScreen(

                onNavigateToLogin = {

                    navController.navigate(Screen.Login.route) {

                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }

                    }

                }

            )

        }


        composable(Screen.Login.route) {

            ConnexionScreen(

                onLoginSuccess = {

                    navController.navigate(Screen.Home.route) {

                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }

                    }

                },

                onRegisterClick = {

                    navController.navigate(Screen.Register.route)

                }

            )

        }


        composable(Screen.Register.route) {

            InscriptionScreen(

                onRegisterSuccess = {

                    navController.navigate(Screen.Home.route) {

                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }

                    }

                },

                onRetourConnexion = {

                    navController.popBackStack()

                }

            )

        }


        composable(Screen.Home.route) {

            AccueilScreen(

                onSignalar = {
                    navController.navigate(Screen.Signaler.route)
                },

                onVoirIncidents = {
                    navController.navigate(Screen.Liste.route)
                },

                onCarte = {
                    navController.navigate(Screen.Carte.route)
                },

                onProfil = {
                    navController.navigate(Screen.Profil.route)
                },

                onLogout = {

                    navController.navigate(Screen.Login.route) {

                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }

                    }

                }

            )

        }


        composable(Screen.Signaler.route) {

            SignalerIncidentScreen(

                onRetour = {
                    navController.popBackStack()
                },

                onCamera = {
                    navController.navigate(Screen.Camera.route)
                }

            )

        }


        composable(Screen.Liste.route) {

            ListeIncidentsScreen(

                onRetour = {
                    navController.popBackStack()
                }

            )

        }


        composable(Screen.Camera.route) {

            CameraScreen()

        }


        composable(Screen.Carte.route) {

            CarteIncidentsScreen(

                onRetour = {
                    navController.popBackStack()
                }

            )

        }


        composable(Screen.Profil.route) {

            ProfilScreen()

        }

    }
}