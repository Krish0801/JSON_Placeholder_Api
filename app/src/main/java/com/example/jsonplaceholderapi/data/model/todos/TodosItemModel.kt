package com.example.jsonplaceholderapi.data.model.todos


import com.google.gson.annotations.SerializedName

data class TodosItemModel(
    @SerializedName("completed")
    val completed: Boolean? = false,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
)