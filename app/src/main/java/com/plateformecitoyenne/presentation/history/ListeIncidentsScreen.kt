package com.plateformecitoyenne.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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


    var message by remember {
        mutableStateOf("")
    }


    val scope = rememberCoroutineScope()



    suspend fun chargerIncidents() {

        try {

            incidents = RetrofitClient.apiService.getIncidents()

        } catch (e: Exception) {

            e.printStackTrace()
            message = "Erreur lors du chargement des incidents"

        }

    }



    LaunchedEffect(Unit) {

        chargerIncidents()

    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
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



        if (message.isNotEmpty()) {

            Text(
                text = message,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))
        }




        incidents.forEach { incident ->


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {


                Column(
                    modifier = Modifier.padding(15.dp)
                ) {


                    Text(
                        text = "Titre : ${incident.titre}",
                        style = MaterialTheme.typography.titleMedium
                    )


                    Spacer(modifier = Modifier.height(4.dp))


                    Text(
                        text = "Description : ${incident.description}"
                    )


                    Text(
                        text = "Priorité : ${incident.priorite}"
                    )


                    Text(
                        text = "Statut : ${incident.statut}"
                    )


                    Text(
                        text = "👍 Soutiens : ${incident.nombreVotes}"
                    )



                    Spacer(modifier = Modifier.height(12.dp))



                    Button(
                        onClick = {


                            scope.launch {

                                try {


                                    val reponse =
                                        RetrofitClient.apiService.voterIncident(
                                            incidentId = incident.id ?: 0L,
                                            utilisateurId = 1L
                                        )


                                    message = reponse


                                    // Actualiser la liste après le vote
                                    chargerIncidents()


                                } catch (e: Exception) {


                                    e.printStackTrace()

                                    message =
                                        "Erreur lors de l'envoi du vote"

                                }

                            }

                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("👍 Soutenir")

                    }

                }

            }


            Spacer(modifier = Modifier.height(10.dp))

        }

    }

}