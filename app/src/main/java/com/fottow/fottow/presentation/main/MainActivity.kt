package com.fottow.fottow.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fottow.fottow.presentation.theme.FottowTheme
import org.koin.androidx.compose.KoinAndroidContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FottowTheme {
                KoinAndroidContext {
                    MainScreen()
                }
            }
        }
    }

}

