package com.plateformecitoyenne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.plateformecitoyenne.navigation.AppNavigation
import com.google.firebase.FirebaseApp
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Initialisation Firebase
        FirebaseApp.initializeApp(this)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}