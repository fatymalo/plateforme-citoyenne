package com.plateformecitoyenne.presentation.incident

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.plateformecitoyenne.repository.IncidentRepository

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

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        imageUri = uri
    }


    var categorie by remember { mutableStateOf(categories[0]) }
    var priorite by remember { mutableStateOf(priorites[1]) }

    var categorieExpanded by remember { mutableStateOf(false) }
    var prioriteExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Button(
            onClick = onRetour
        ) {
            Text("⬅ Retour")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Signaler un incident",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    onCamera()
                }
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

        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = categorieExpanded,
            onExpandedChange = {
                categorieExpanded = !categorieExpanded
            }
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
                onDismissRequest = {
                    categorieExpanded = false
                }
            ) {

                categories.forEach {

                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            categorie = it
                            categorieExpanded = false
                        }
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = prioriteExpanded,
            onExpandedChange = {
                prioriteExpanded = !prioriteExpanded
            }
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
                onDismissRequest = {
                    prioriteExpanded = false
                }
            ) {

                priorites.forEach {

                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            priorite = it
                            prioriteExpanded = false
                        }
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(24.dp))

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
                            priorite = priorite
                        )

                        if (result.isSuccess) {

                            message = "Signalement envoyé avec succès ✅"

                            titre = ""
                            description = ""

                        } else {

                            message = result.exceptionOrNull()?.message
                        }
                    }
                }
            }
        ) {
            Text("📤 Envoyer le signalement")
        }


        message?.let {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = it,
                color = if (it.contains("succès"))
                    Color(0xFF2E7D32)
                else
                    MaterialTheme.colorScheme.error
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

    }
}