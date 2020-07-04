package mx.dev1.pokedex.core.domain.results

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.domain.Pokemon

data class PokedexResult (
    @SerializedName("pokemon_entries")
    val pokemonEntries: MutableList<Pokemon>
)