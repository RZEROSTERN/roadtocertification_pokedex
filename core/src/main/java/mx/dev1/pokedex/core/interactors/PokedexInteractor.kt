package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokedexResult

interface PokedexInteractor {
    fun getPokedex(pokedex: String): Observable<PokedexResult>
}