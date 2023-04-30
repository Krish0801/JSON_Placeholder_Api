package com.example.jsonplaceholderapi.data.model.posts


import com.google.gson.annotations.SerializedName

data class PostsItemModel(
    @SerializedName("body")
    val body: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
)