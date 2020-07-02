package mx.dev1.pokedex.core.domain

import com.google.gson.annotations.SerializedName

data class Region (
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)