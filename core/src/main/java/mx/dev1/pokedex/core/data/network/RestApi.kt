package mx.dev1.pokedex.core.data.network

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.entities.results.RegionResult
import retrofit2.http.GET

interface RestApi {
    @GET("/region")
    fun getRegions(): Observable<RegionResult>
}