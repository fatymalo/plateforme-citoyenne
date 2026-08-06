package com.plateformecitoyenne.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfilScreen(
    onModifierProfil: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // Avatar
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFE1BEE7)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profil",
                modifier = Modifier.size(90.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Mon Profil",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        ProfilItem(
            titre = "Nom",
            valeur = "Faty Samb"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfilItem(
            titre = "Email",
            valeur = "faty@gmail.com"
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfilItem(
            titre = "Signalements envoyés",
            valeur = "0"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onModifierProfil()
            }
        ) {
            Text("Modifier le profil")
        }
    }
}

@Composable
fun ProfilItem(
    titre: String,
    valeur: String
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = titre,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = valeur,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}