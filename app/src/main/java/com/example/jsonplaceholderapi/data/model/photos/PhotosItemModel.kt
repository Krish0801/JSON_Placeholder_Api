package com.example.jsonplaceholderapi.data.model.photos


import com.google.gson.annotations.SerializedName

data class PhotosItemModel(
    @SerializedName("albumId")
    val albumId: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String? = ""
)