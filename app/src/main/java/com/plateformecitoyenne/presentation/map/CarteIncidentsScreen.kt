package com.plateformecitoyenne.presentation.map

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarteIncidentsScreen(
    onRetour: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🗺️ Carte des incidents",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Les incidents avec localisation apparaîtront ici."
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = onRetour) {
            Text("Retour")
        }
    }
}