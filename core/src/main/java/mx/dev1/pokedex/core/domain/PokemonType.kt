package mx.dev1.pokedex.core.domain

import com.google.gson.annotations.SerializedName

data class PokemonType (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)