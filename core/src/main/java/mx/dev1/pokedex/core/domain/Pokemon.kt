package mx.dev1.pokedex.core.domain

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.domain.results.PokemonResult

data class Pokemon (
    @SerializedName("entry_number")
    val entryNumber: Integer,
    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpecies,
    var pokemonDetails: PokemonResult,
    var pokemonImage: String
)