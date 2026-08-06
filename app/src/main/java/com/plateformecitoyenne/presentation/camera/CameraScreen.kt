package com.plateformecitoyenne.presentation.camera

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box

@Composable
fun CameraScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        CameraPreview()

    }

}