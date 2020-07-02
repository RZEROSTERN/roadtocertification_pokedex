package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.RegionDetailedResult
import mx.dev1.pokedex.core.domain.results.RegionResult

interface RegionRepository {
    fun getRegions(): Observable<RegionResult>

    fun getRegionByName(region: String): Observable<RegionDetailedResult>
}