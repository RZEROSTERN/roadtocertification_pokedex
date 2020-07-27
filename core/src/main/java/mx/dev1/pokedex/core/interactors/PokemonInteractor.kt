package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokemonResult

interface PokemonInteractor {
    fun getPokemon(pokemon: String): Observable<PokemonResult>
}