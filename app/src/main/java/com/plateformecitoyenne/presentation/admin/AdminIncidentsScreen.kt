package com.plateformecitoyenne.presentation.admin


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.plateformecitoyenne.model.Incident
import com.plateformecitoyenne.network.RetrofitClient
import kotlinx.coroutines.launch


@Composable
fun AdminIncidentsScreen(
    onRetour: () -> Unit,
    onModifier: (Long) -> Unit
) {


    var incidents by remember {
        mutableStateOf<List<Incident>>(emptyList())
    }


    var message by remember {
        mutableStateOf("")
    }


    val scope = rememberCoroutineScope()



    LaunchedEffect(Unit) {

        try {

            incidents =
                RetrofitClient.apiService.getAdminIncidents()

        } catch (e: Exception) {

            message = "Erreur chargement incidents"

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



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Text(
            text = "Gestion des incidents",
            style = MaterialTheme.typography.headlineMedium
        )



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        if(message.isNotEmpty()) {

            Text(message)

        }



        incidents.forEach { incident ->



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {



                Column(
                    modifier = Modifier.padding(15.dp)
                ) {



                    Text(
                        text = "Titre : ${incident.titre}"
                    )


                    Text(
                        text = "Votes 👍 : ${incident.nombreVotes}"
                    )


                    Text(
                        text = "Statut : ${incident.statut}"
                    )



                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )



                    // Modifier incident
                    Button(
                        onClick = {

                            onModifier(
                                incident.id ?: 0L
                            )

                        },

                        modifier = Modifier.fillMaxWidth()

                    ) {

                        Text("✏️ Modifier")

                    }



                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )



                    // Marquer traité directement
                    Button(
                        onClick = {

                            scope.launch {

                                try {

                                    RetrofitClient.apiService.modifierStatut(
                                        incident.id ?: 0L,
                                        "TRAITE"
                                    )


                                    message =
                                        "Incident marqué comme traité"


                                    incidents =
                                        RetrofitClient.apiService.getAdminIncidents()


                                } catch (e: Exception) {

                                    message =
                                        "Erreur modification"

                                }

                            }

                        },

                        modifier = Modifier.fillMaxWidth()

                    ) {

                        Text("✅ Marquer traité")

                    }


                }

            }


        }


    }

}