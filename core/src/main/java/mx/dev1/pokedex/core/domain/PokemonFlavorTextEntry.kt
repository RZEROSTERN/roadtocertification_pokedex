package mx.dev1.pokedex.core.domain

import com.google.gson.annotations.SerializedName

data class PokemonFlavorTextEntry (
    @SerializedName("flavor_text")
    val flavor_text: String
)