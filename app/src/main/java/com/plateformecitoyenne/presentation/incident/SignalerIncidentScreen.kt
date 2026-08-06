package com.plateformecitoyenne.presentation.incident

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.plateformecitoyenne.repository.IncidentRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalerIncidentScreen(
    onRetour: () -> Unit,
    onCamera: () -> Unit
) {

    var titre by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var message by remember { mutableStateOf<String?>(null) }

    // Localisation
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    val repository = remember { IncidentRepository() }
    val scope = rememberCoroutineScope()

    val categories = listOf(
        "Route dégradée",
        "Éclairage public",
        "Dépôt d'ordures",
        "Inondation",
        "Insécurité",
        "Autre"
    )

    val priorites = listOf(
        "Faible",
        "Moyenne",
        "Élevée",
        "Urgente"
    )

    var categorie by remember { mutableStateOf(categories[0]) }
    var priorite by remember { mutableStateOf(priorites[1]) }

    var categorieExpanded by remember { mutableStateOf(false) }
    var prioriteExpanded by remember { mutableStateOf(false) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Button(onClick = onRetour) {
            Text("⬅ Retour")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Signaler un incident",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------------- PHOTO ----------------

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFECEFF1)),
                contentAlignment = Alignment.Center
            ) {

                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Aucune photo")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Button(
                modifier = Modifier.weight(1f),
                onClick = onCamera
            ) {
                Text("📷 Photo")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Text("🖼 Galerie")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------------- TITRE ----------------

        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------------- DESCRIPTION ----------------

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------------- CATEGORIE ----------------

        ExposedDropdownMenuBox(
            expanded = categorieExpanded,
            onExpandedChange = { categorieExpanded = !categorieExpanded }
        ) {

            OutlinedTextField(
                value = categorie,
                onValueChange = {},
                readOnly = true,
                label = { Text("Catégorie") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = categorieExpanded,
                onDismissRequest = { categorieExpanded = false }
            ) {

                categories.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            categorie = item
                            categorieExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ---------------- PRIORITE ----------------

        ExposedDropdownMenuBox(
            expanded = prioriteExpanded,
            onExpandedChange = { prioriteExpanded = !prioriteExpanded }
        ) {

            OutlinedTextField(
                value = priorite,
                onValueChange = {},
                readOnly = true,
                label = { Text("Priorité") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = prioriteExpanded,
                onDismissRequest = { prioriteExpanded = false }
            ) {

                priorites.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            priorite = item
                            prioriteExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ---------------- LOCALISATION ----------------

        Text("📍 Localisation")

        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------------- ENVOI ----------------

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {

                scope.launch {

                    if (titre.isBlank() || description.isBlank()) {
                        message = "Veuillez remplir tous les champs"
                    } else {

                        val result = repository.envoyerIncident(
                            titre = titre,
                            description = description,
                            categorie = categorie,
                            priorite = priorite,
                            latitude = latitude.toDoubleOrNull(),
                            longitude = longitude.toDoubleOrNull()
                        )

                        if (result.isSuccess) {

                            message = "Signalement envoyé avec succès ✅"

                            titre = ""
                            description = ""
                            latitude = ""
                            longitude = ""
                            imageUri = null

                        } else {
                            message = result.exceptionOrNull()?.message
                        }
                    }
                }
            }
        ) {
            Text("📤 Envoyer le signalement")
        }

        // ---------------- MESSAGE ----------------

        message?.let { text ->

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = text,
                color = if (text.contains("succès"))
                    Color(0xFF2E7D32)
                else
                    MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}
