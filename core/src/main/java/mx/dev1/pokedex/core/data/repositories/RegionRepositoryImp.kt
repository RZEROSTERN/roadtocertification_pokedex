package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.entities.results.RegionDetailedResult
import mx.dev1.pokedex.core.data.entities.results.RegionResult
import mx.dev1.pokedex.core.data.network.RestApi
import mx.dev1.pokedex.core.data.network.RestApiImp

class RegionRepositoryImp: RegionRepository {
    private var apiRequest: RestApi = RestApiImp.getClient().create(RestApi::class.java)

    override fun getRegions(): Observable<RegionResult> {
        return apiRequest.getRegions()
    }

    override fun getRegionByName(region: String): Observable<RegionDetailedResult> {
        return apiRequest.getDetailedRegion(region)
    }
}