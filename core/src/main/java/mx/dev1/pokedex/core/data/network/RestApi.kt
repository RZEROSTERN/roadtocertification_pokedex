package mx.dev1.pokedex.core.data.network

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.RegionDetailedResult
import mx.dev1.pokedex.core.domain.results.RegionResult
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("region")
    fun getRegions(): Observable<RegionResult>

    @GET("region/{region}")
    fun getDetailedRegion(@Path("region") region: String): Observable<RegionDetailedResult>
}