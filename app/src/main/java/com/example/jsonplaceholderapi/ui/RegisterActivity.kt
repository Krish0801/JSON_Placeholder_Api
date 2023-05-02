package com.example.jsonplaceholderapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.jsonplaceholderapi.ui.registerPage.RegisterPage
import com.example.jsonplaceholderapi.ui.theme.JSONPlaceholderApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            JSONPlaceholderApiTheme {
               RegisterPage(navController = navController)
            }
        }
    }
}