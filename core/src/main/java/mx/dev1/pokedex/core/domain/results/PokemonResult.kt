package mx.dev1.pokedex.core.domain.results

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.domain.PokemonStat
import mx.dev1.pokedex.core.domain.Sprite

data class PokemonResult (
    @SerializedName("id")
    val id: Integer,
    @SerializedName("base_experience")
    val baseExperience: Integer,
    @SerializedName("height")
    val height: Integer,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprite,
    @SerializedName("types")
    val types: MutableList<PokemonTypeItem>,
    @SerializedName("weight")
    val weight: Integer,
    @SerializedName("stats")
    val stats: MutableList<PokemonStat>,
    var image: String,
    var species: PokemonSpeciesResult
)