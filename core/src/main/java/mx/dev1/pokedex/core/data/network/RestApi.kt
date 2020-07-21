package mx.dev1.pokedex.core.data.network

import io.reactivex.Observable
import mx.dev1.pokedex.core.domain.results.PokedexResult
import mx.dev1.pokedex.core.domain.results.PokemonResult
import mx.dev1.pokedex.core.domain.results.RegionDetailedResult
import mx.dev1.pokedex.core.domain.results.RegionResult
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("region")
    fun getRegions(): Observable<RegionResult>

    @GET("region/{region}")
    fun getDetailedRegion(@Path("region") region: String): Observable<RegionDetailedResult>

    @GET("pokedex/{pokedex}")
    fun getPokedex(@Path("pokedex") pokedex: String): Observable<PokedexResult>

    @GET("pokemon/{pokemon}")
    fun getPokemonByName(@Path("pokemon") pokemon: String): Observable<PokemonResult>
}