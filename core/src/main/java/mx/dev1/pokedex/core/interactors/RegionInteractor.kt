package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.RegionDetailedResult
import mx.dev1.pokedex.core.domain.results.RegionResult

interface RegionInteractor {
    fun getRegions(): Observable<RegionResult>

    fun getDetailedRegion(region: String): Observable<RegionDetailedResult>
}