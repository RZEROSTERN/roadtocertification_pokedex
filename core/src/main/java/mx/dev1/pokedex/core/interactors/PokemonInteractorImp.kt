package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.repositories.PokemonRepositoryImp
import mx.dev1.pokedex.core.domain.results.PokemonResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PokemonInteractorImp(private val pokemonRepositoryImp: PokemonRepositoryImp): PokemonInteractor {
    val logger: Logger = LoggerFactory.getLogger(RegionInteractorImp::class.java.simpleName)

    override fun getPokemon(pokemon: String): Observable<PokemonResult> {
        return pokemonRepositoryImp.getPokemon(pokemon)
            .doOnNext { response -> logger.debug(response.toString()) }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }
}