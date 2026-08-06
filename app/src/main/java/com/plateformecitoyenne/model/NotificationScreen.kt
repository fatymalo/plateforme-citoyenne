package com.plateformecitoyenne.presentation.notification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateformecitoyenne.model.Notification
import com.plateformecitoyenne.network.RetrofitClient


@Composable
fun NotificationScreen(
    utilisateurId: Long,
    onRetour: () -> Unit
) {


    var notifications by remember {
        mutableStateOf<List<Notification>>(emptyList())
    }


    var message by remember {
        mutableStateOf("")
    }



    LaunchedEffect(Unit) {

        try {

            notifications =
                RetrofitClient.apiService
                    .getNotifications(utilisateurId)


        } catch (e: Exception) {

            e.printStackTrace()

            message = "Erreur chargement notifications"

        }

    }




    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(
                rememberScrollState()
            )

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
            text = "🔔 Notifications",
            style = MaterialTheme.typography.headlineMedium
        )



        Spacer(
            modifier = Modifier.height(20.dp)
        )



        if(message.isNotEmpty()) {

            Text(message)

        }



        notifications.forEach { notification ->


            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {


                Column(

                    modifier = Modifier
                        .padding(16.dp)

                ) {


                    Text(
                        text = notification.titre,
                        style = MaterialTheme.typography.titleMedium
                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(
                        text = notification.message
                    )



                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(
                        text =
                            if(notification.lue)
                                "Lu"
                            else
                                "Nouveau"
                    )


                }


            }


        }


    }

}