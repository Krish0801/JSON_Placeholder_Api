package com.example.jsonplaceholderapi.sideDrawer

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jsonplaceholderapi.R
import com.example.jsonplaceholderapi.navigation.NavigationItem
import com.example.jsonplaceholderapi.ui.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){

    val items = listOf(
        NavigationItem.Users,
        NavigationItem.Photos,
        NavigationItem.Posts,
        NavigationItem.Albums,
        NavigationItem.Comments,
        NavigationItem.Todos
    )
    val headerColor= Color(0xFF0C3954)
    val backgroundColor= Color(0xFFEDF6FC)
    val textColor= Color(0xFFE68753)

    Column(
        modifier = Modifier
            .background(backgroundColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(headerColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
                Text(
                    text = "JSON Placeholder Api",
                    color = textColor,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }


        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items, selected = currentRoute == items.route, onItemClick = {

                navController.navigate(items.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }

            })
        }

        Spacer(modifier = Modifier.weight(1f))

        LogoutButton()


    }
}
@Composable
fun LogoutButton() {
    val context = LocalContext.current
    val headerColor= Color(0xFF0C3954)

    // State variable to track if the logout confirmation dialog is open
    var isDialogOpen by remember { mutableStateOf(false) }

    Text(
        text = "Logout",
        color = headerColor,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(18.dp)
            .clickable {
                // Show the logout confirmation dialog
                isDialogOpen = true
            }
            .fillMaxWidth()
    )

    // Display the logout confirmation dialog if it is open
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog if the user taps outside it
                isDialogOpen = false
            },
            title = {
                Text(text = "Confirm Logout")
            },
            text = {
                Text(text = "Are you sure you want to log out?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Perform logout action here
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                        (context as Activity).finish()
                        Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show()

                        // Dismiss the dialog after logout
                        isDialogOpen = false
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Dismiss the dialog if the user taps cancel
                        isDialogOpen = false
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}



