package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.entities.results.RegionResult

interface RegionRepository {
    fun getRegions(): Observable<RegionResult>
}