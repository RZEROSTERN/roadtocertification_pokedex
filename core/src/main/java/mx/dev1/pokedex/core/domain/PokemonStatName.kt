package mx.dev1.pokedex.core.domain

import com.google.gson.annotations.SerializedName

data class PokemonStatName (
    @SerializedName("name")
    val name: String
)