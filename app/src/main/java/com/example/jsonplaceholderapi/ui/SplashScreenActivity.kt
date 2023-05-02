package com.example.jsonplaceholderapi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}


@Composable
fun SplashScreen(navigateTo: () -> Unit) {

    val splashColor = Color(0xFFFEFEFE)
    Surface(color = splashColor) {

        Column(
        ) {

            Image(
                painter = painterResource(id = com.example.jsonplaceholderapi.R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )

            Image(
                painter = painterResource(id = com.example.jsonplaceholderapi.R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )

        }

        LaunchedEffect(Unit) {
            // Simulate a delay for the splash screen
            delay(2000)

            // Navigate to the next activity
            navigateTo()
        }
    }
}



