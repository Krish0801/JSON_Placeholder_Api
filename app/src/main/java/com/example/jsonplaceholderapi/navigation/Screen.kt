package com.example.jsonplaceholderapi.navigation

sealed class Screen(val route: String) {

    object LoginPage : Screen(route = "Login_Page")

    object RegisterPage : Screen(route = "Register_Page")

}