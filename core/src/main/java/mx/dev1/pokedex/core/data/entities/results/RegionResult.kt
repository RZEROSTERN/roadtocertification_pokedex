package mx.dev1.pokedex.core.data.entities.results

import com.google.gson.annotations.SerializedName
import mx.dev1.pokedex.core.data.entities.Region

data class RegionResult (
    @SerializedName("count")
    var count: Integer,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var results: MutableList<Region>
)