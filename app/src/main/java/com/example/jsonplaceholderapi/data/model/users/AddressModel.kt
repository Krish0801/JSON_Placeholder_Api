package com.example.jsonplaceholderapi.data.model.users


import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("city")
    val city: String? = "",
    @SerializedName("geo")
    val geo: GeoModel? = GeoModel(),
    @SerializedName("street")
    val street: String? = "",
    @SerializedName("suite")
    val suite: String? = "",
    @SerializedName("zipcode")
    val zipcode: String? = ""
)