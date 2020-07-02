package mx.dev1.pokedex.core.domain.results

import mx.dev1.pokedex.core.domain.Pokedex

data class RegionDetailedResult (
    var pokedexes: MutableList<Pokedex>
)