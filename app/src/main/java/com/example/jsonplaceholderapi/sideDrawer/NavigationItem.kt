package com.example.jsonplaceholderapi.sideDrawer

import com.example.jsonplaceholderapi.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String )
{
    object Users : NavigationItem("users", R.drawable.ic_profile, "Users")
    object Photos : NavigationItem("photos", R.drawable.ic_photos, "Photos")
    object Posts : NavigationItem("posts", R.drawable.ic_posts, "Posts")
    object Albums : NavigationItem("albums", R.drawable.ic_albums, "Albums")
    object Comments : NavigationItem("comments", R.drawable.ic_comments, "Comments")
    object Todos : NavigationItem("todos", R.drawable.ic_todos, "Todos")
}