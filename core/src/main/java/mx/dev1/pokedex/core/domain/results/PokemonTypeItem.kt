package mx.dev1.pokedex.core.domain.results

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.domain.PokemonType

data class PokemonTypeItem (
    @SerializedName("slot")
    val slot: Integer,
    @SerializedName("type")
    val type: PokemonType
)