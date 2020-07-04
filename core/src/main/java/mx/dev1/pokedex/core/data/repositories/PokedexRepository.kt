package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokedexResult

interface PokedexRepository {
    fun getPokedex(pokedex: String): Observable<PokedexResult>
}