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
import com.plateformecitoyenne.profil.EditProfilScreen

import com.plateformecitoyenne.presentation.admin.AdminHomeScreen
import com.plateformecitoyenne.presentation.admin.AdminIncidentsScreen
import com.plateformecitoyenne.presentation.admin.ModifierIncidentScreen

import com.plateformecitoyenne.presentation.notification.NotificationScreen


@Composable
fun AppNavigation() {


    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {


        // ==========================
        // SPLASH
        // ==========================

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



        // ==========================
        // LOGIN
        // ==========================

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




        // ==========================
        // INSCRIPTION
        // ==========================

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




        // ==========================
        // ACCUEIL CITOYEN
        // ==========================

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


                onNotifications = {

                    navController.navigate(Screen.Notification.route)

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




        // ==========================
        // SIGNALER INCIDENT
        // ==========================

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





        // ==========================
        // LISTE INCIDENTS
        // ==========================

        composable(Screen.Liste.route) {


            ListeIncidentsScreen(

                onRetour = {

                    navController.popBackStack()

                }

            )

        }





        // ==========================
        // CAMERA
        // ==========================

        composable(Screen.Camera.route) {

            CameraScreen()

        }





        // ==========================
        // CARTE
        // ==========================

        composable(Screen.Carte.route) {


            CarteIncidentsScreen(

                onRetour = {

                    navController.popBackStack()

                }

            )

        }





        // ==========================
        // PROFIL
        // ==========================

        composable(Screen.Profil.route) {


            ProfilScreen(

                onModifierProfil = {

                    navController.navigate(
                        Screen.EditProfil.route
                    )

                }

            )

        }




        // ==========================
        // MODIFIER PROFIL
        // ==========================

        composable(Screen.EditProfil.route) {


            EditProfilScreen(

                onRetour = {

                    navController.popBackStack()

                }

            )

        }





        // ==========================
        // NOTIFICATIONS
        // ==========================

        composable(Screen.Notification.route) {


            NotificationScreen(

                utilisateurId = 1L,

                onRetour = {

                    navController.popBackStack()

                }

            )

        }





        // ==========================
        // ADMIN HOME
        // ==========================

        composable(Screen.AdminHome.route) {


            AdminHomeScreen(

                onVoirIncidents = {

                    navController.navigate(
                        Screen.AdminIncidents.route
                    )

                },


                onLogout = {

                    navController.navigate(Screen.Login.route) {

                        popUpTo(Screen.AdminHome.route) {

                            inclusive = true

                        }

                    }

                }

            )

        }





        // ==========================
        // ADMIN INCIDENTS
        // ==========================

        composable(Screen.AdminIncidents.route) {


            AdminIncidentsScreen(

                onRetour = {

                    navController.popBackStack()

                },


                onModifier = { incidentId ->


                    navController.navigate(
                        Screen.ModifierIncident.createRoute(
                            incidentId
                        )
                    )


                }

            )

        }





        // ==========================
        // MODIFIER INCIDENT
        // ==========================

        composable(
            Screen.ModifierIncident.route
        ) { backStackEntry ->


            val id =
                backStackEntry.arguments
                    ?.getString("id")
                    ?.toLongOrNull()
                    ?: 0L



            ModifierIncidentScreen(

                incidentId = id,


                onRetour = {

                    navController.popBackStack()

                }

            )


        }


    }

}