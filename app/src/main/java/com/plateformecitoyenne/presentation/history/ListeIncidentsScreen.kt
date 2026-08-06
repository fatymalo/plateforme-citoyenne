package com.plateformecitoyenne.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateformecitoyenne.model.Incident
import com.plateformecitoyenne.network.RetrofitClient
import kotlinx.coroutines.launch


@Composable
fun ListeIncidentsScreen(
    onRetour: () -> Unit
) {

    var incidents by remember {
        mutableStateOf<List<Incident>>(emptyList())
    }

    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {

        scope.launch {

            try {

                incidents = RetrofitClient.apiService.getIncidents()

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {


        Button(
            onClick = {
                onRetour()
            }
        ) {
            Text("⬅ Retour")
        }


        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "Liste des incidents",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(modifier = Modifier.height(20.dp))


        incidents.forEach { incident ->


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {

                Column(
                    modifier = Modifier.padding(15.dp)
                ) {

                    Text("Titre : ${incident.titre}")

                    Text("Description : ${incident.description}")

                    Text("Priorité : ${incident.priorite}")

                    Text("Statut : ${incident.statut}")

                }
            }
        }
    }
}