package com.example.jsonplaceholderapi.data.model.users


import com.google.gson.annotations.SerializedName

data class CompanyModel(
    @SerializedName("bs")
    val bs: String? = "",
    @SerializedName("catchPhrase")
    val catchPhrase: String? = "",
    @SerializedName("name")
    val name: String? = ""
)