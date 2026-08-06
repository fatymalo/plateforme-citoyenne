package com.plateformecitoyenne

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun IncidentScreen() {

    var titre by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var adresse by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {


        Text(
            text = "Signaler un incident",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        OutlinedTextField(
            value = titre,
            onValueChange = {
                titre = it
            },
            label = {
                Text("Titre")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text("Description")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        OutlinedTextField(
            value = adresse,
            onValueChange = {
                adresse = it
            },
            label = {
                Text("Adresse")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Envoyer le signalement")

        }

    }
}
