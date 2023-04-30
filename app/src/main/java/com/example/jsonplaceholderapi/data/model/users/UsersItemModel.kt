package com.example.jsonplaceholderapi.data.model.users


import com.google.gson.annotations.SerializedName

data class UsersItemModel(
    @SerializedName("address")
    val address: AddressModel? = AddressModel(),
    @SerializedName("company")
    val company: CompanyModel? = CompanyModel(),
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("website")
    val website: String? = ""
)