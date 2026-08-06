package com.plateformecitoyenne.presentation.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AdminHomeScreen(
    onVoirIncidents: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {


        Text(
            text = "Espace Administrateur",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(
            modifier = Modifier.height(30.dp)
        )


        Button(
            onClick = {
                onVoirIncidents()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("📋 Gérer les incidents")

        }



        Spacer(
            modifier = Modifier.height(15.dp)
        )



        Button(
            onClick = {
                onLogout()
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Déconnexion")

        }

    }
}