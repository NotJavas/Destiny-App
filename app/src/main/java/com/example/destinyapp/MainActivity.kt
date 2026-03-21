package com.example.destinyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.destinyapp.ui.navigation.DestinyNavHost
import com.example.destinyapp.ui.theme.DestinyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DestinyAppTheme {
                DestinyNavHost()
            }
        }
    }
}
