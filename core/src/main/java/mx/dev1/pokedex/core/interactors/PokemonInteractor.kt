package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokemonResult
import mx.dev1.pokedex.core.domain.results.PokemonSpeciesResult

interface PokemonInteractor {
    fun getPokemon(pokemon: String): Observable<PokemonResult>

    fun getPokemonSpecies(pokemon: String): Observable<PokemonSpeciesResult>
}