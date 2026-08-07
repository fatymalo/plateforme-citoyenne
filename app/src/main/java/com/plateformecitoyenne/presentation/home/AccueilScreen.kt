package com.plateformecitoyenne.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AccueilScreen(
    onSignalar: () -> Unit,
    onVoirIncidents: () -> Unit,
    onCarte: () -> Unit,
    onProfil: () -> Unit,
    onNotifications: () -> Unit,
    onAdmin: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F7FA),
                        Color.White
                    )
                )
            )
            .padding(20.dp)
    ) {

        // ==========================
        // EN-TÊTE
        // ==========================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = "Bonjour 👋",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Bienvenue sur CitizenConnect",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            FilledTonalIconButton(
                onClick = {
                    onNotifications()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }


        Spacer(modifier = Modifier.height(30.dp))


        // ==========================
        // CARTES PRINCIPALES
        // ==========================

        HomeCard(
            title = "📢 Signaler un incident",
            description = "Créer un nouveau signalement",
            color = Color(0xFF1976D2),
            onClick = onSignalar
        )


        Spacer(modifier = Modifier.height(18.dp))


        HomeCard(
            title = "📋 Mes incidents",
            description = "Consulter tous les signalements",
            color = Color(0xFF43A047),
            onClick = onVoirIncidents
        )


        Spacer(modifier = Modifier.height(18.dp))


        HomeCard(
            title = "🗺️ Carte des incidents",
            description = "Visualiser les incidents",
            color = Color(0xFFFF9800),
            onClick = onCarte
        )


        Spacer(modifier = Modifier.height(18.dp))


        HomeCard(
            title = "👤 Mon profil",
            description = "Modifier mes informations",
            color = Color(0xFF8E24AA),
            onClick = onProfil
        )


        Spacer(modifier = Modifier.height(18.dp))


        HomeCard(
            title = "🛠️ Espace administrateur",
            description = "Gérer les signalements citoyens",
            color = Color(0xFFD32F2F),
            onClick = onAdmin
        )


        Spacer(modifier = Modifier.weight(1f))


        // ==========================
        // DÉCONNEXION
        // ==========================

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLogout
        ) {

            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Déconnexion"
            )


            Spacer(modifier = Modifier.width(10.dp))


            Text("Déconnexion")
        }
    }
}


@Composable
fun HomeCard(
    title: String,
    description: String,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )


            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = description,
                color = Color.White
            )
        }
    }
}