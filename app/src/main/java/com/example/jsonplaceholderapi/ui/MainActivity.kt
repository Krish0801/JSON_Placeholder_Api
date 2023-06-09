package com.example.jsonplaceholderapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.jsonplaceholderapi.navigation.NavigationPage
import com.example.jsonplaceholderapi.ui.theme.JSONPlaceholderApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JSONPlaceholderApiTheme {
                NavigationPage()
            }
        }
    }
}



