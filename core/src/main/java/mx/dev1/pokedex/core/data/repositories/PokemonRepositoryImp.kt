package mx.dev1.pokedex.core.data.repositories

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.network.RestApi
import mx.dev1.pokedex.core.data.network.RestApiImp
import mx.dev1.pokedex.core.domain.results.PokemonResult

class PokemonRepositoryImp: PokemonRepository {
    private var apiRequest: RestApi = RestApiImp.getClient().create(RestApi::class.java)

    override fun getPokemon(name: String): Observable<PokemonResult> {
        return apiRequest.getPokemonByName(name)
    }
}