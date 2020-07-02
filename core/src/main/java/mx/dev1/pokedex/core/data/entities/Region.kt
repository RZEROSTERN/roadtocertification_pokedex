package mx.dev1.pokedex.core.data.entities

import com.google.gson.annotations.SerializedName

data class Region (
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)