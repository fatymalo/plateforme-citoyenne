package com.plateformecitoyenne.profil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditProfilScreen(
    onRetour: () -> Unit
) {

    var nom by remember { mutableStateOf("Faty Malo Samb") }
    var email by remember { mutableStateOf("utilisateur@email.com") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Modifier le profil",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // TODO : sauvegarder les modifications
                onRetour()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enregistrer")
        }

        OutlinedButton(
            onClick = { onRetour() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Annuler")
        }
    }
}