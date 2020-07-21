package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokemonResult

interface PokemonRepository {
    fun getPokemon(name: String): Observable<PokemonResult>
}