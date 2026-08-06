package com.plateformecitoyenne.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.plateformecitoyenne.R

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(2500)
        onNavigateToLogin()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_citizenconnect),
            contentDescription = "Logo CitizenConnect",
            modifier = Modifier.size(140.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "CitizenConnect",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        Text(
            text = "Ensemble pour une ville meilleure",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}