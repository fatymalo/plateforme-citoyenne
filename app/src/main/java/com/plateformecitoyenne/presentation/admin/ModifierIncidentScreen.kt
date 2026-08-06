package com.plateformecitoyenne.presentation.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateformecitoyenne.network.RetrofitClient
import kotlinx.coroutines.launch


@Composable
fun ModifierIncidentScreen(
    incidentId: Long,
    onRetour: () -> Unit
) {


    val statuts = listOf(
        "EN_ATTENTE",
        "EN_COURS",
        "TRAITE",
        "REJETE"
    )


    val priorites = listOf(
        "BASSE",
        "MOYENNE",
        "HAUTE",
        "URGENTE"
    )



    var statut by remember {
        mutableStateOf("EN_ATTENTE")
    }


    var priorite by remember {
        mutableStateOf("MOYENNE")
    }



    var message by remember {
        mutableStateOf("")
    }



    var statutOuvert by remember {
        mutableStateOf(false)
    }


    var prioriteOuverte by remember {
        mutableStateOf(false)
    }



    val scope = rememberCoroutineScope()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
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
            text = "Modifier incident",
            style = MaterialTheme.typography.headlineMedium
        )



        Spacer(
            modifier = Modifier.height(30.dp)
        )



        Text("Statut")



        Box {

            Button(
                onClick = {
                    statutOuvert = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(statut)

            }


            DropdownMenu(
                expanded = statutOuvert,
                onDismissRequest = {
                    statutOuvert = false
                }
            ) {

                statuts.forEach { item ->

                    DropdownMenuItem(

                        text = {
                            Text(item)
                        },

                        onClick = {

                            statut = item
                            statutOuvert = false

                        }

                    )

                }

            }

        }



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Text("Priorité")



        Box {


            Button(
                onClick = {
                    prioriteOuverte = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(priorite)

            }



            DropdownMenu(
                expanded = prioriteOuverte,
                onDismissRequest = {
                    prioriteOuverte = false
                }
            ) {


                priorites.forEach { item ->


                    DropdownMenuItem(

                        text = {
                            Text(item)
                        },


                        onClick = {

                            priorite = item
                            prioriteOuverte = false

                        }

                    )

                }

            }

        }




        Spacer(
            modifier = Modifier.height(30.dp)
        )




        Button(
            onClick = {


                scope.launch {
                    try {

                        // Modifier le statut
                        RetrofitClient.apiService.modifierStatut(
                            incidentId,
                            statut
                        )

                        // Modifier la priorité
                        RetrofitClient.apiService.modifierPriorite(
                            incidentId,
                            priorite
                        )

                        message = "Incident modifié avec succès"

                    } catch (e: Exception) {
                        e.printStackTrace()
                        message = "Erreur de modification"
                    }
                }

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("💾 Enregistrer")

        }




        Spacer(
            modifier = Modifier.height(20.dp)
        )



        if(message.isNotEmpty()) {

            Text(message)

        }


    }

}