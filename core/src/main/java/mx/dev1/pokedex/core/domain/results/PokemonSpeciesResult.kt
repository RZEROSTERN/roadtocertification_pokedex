package mx.dev1.pokedex.core.domain.results

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.domain.PokemonFlavorTextEntry

data class PokemonSpeciesResult (
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: ArrayList<PokemonFlavorTextEntry>
)