package com.example.jsonplaceholderapi.navigation

import androidx.compose.material.*
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jsonplaceholderapi.sideDrawer.Drawer
import com.example.jsonplaceholderapi.ui.loginPage.LoginPage
import com.example.jsonplaceholderapi.ui.pages.albums.AlbumsScreen
import com.example.jsonplaceholderapi.ui.pages.comments.CommentsScreen
import com.example.jsonplaceholderapi.ui.pages.photos.PhotosScreen
import com.example.jsonplaceholderapi.ui.pages.posts.PostsScreen
import com.example.jsonplaceholderapi.ui.pages.todos.TodosScreen
import com.example.jsonplaceholderapi.ui.pages.users.UsersScreen
import com.example.jsonplaceholderapi.ui.registerPage.RegisterPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(
            DrawerValue.Closed
        )
    )
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val currentRoute = navController.currentDestination?.route

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
        Navigation(navController = navController)
    }
}



@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Users.route) {
        composable(NavigationItem.Users.route) {
            UsersScreen(navController = navController)
        }

        composable(NavigationItem.Photos.route) {
            PhotosScreen(navController = navController)
        }

        composable(NavigationItem.Posts.route) {
            PostsScreen(navController = navController)
        }

        composable(NavigationItem.Albums.route) {
            AlbumsScreen(navController = navController)
        }

        composable(NavigationItem.Comments.route) {
            CommentsScreen(navController = navController)
        }

        composable(NavigationItem.Todos.route) {
            TodosScreen(navController= navController)
        }

        composable(NavigationItem.LoginPage.route){
            LoginPage(navController= navController)
        }

        composable(NavigationItem.RegisterPage.route){
            RegisterPage(navController= navController)
        }
    }
}


@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val topBarColor = Color(0xFF0C3954)
    val textColor = Color(0xFFE88853)
    if (navController.currentDestination?.route !in listOf(NavigationItem.LoginPage.route, NavigationItem.RegisterPage.route)) {
        TopAppBar(
            title = { Text(text = "JSON Placeholder Api", fontSize = 18.sp) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, "")
                }
            },
            backgroundColor = topBarColor,
            contentColor = textColor
        )
    }
}






