package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.repositories.PokemonRepositoryImp
import mx.dev1.pokedex.core.domain.results.PokemonResult
import mx.dev1.pokedex.core.domain.results.PokemonSpeciesResult
import mx.dev1.pokedex.core.utils.Constant
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PokemonInteractorImp(private val pokemonRepositoryImp: PokemonRepositoryImp): PokemonInteractor {
    val logger: Logger = LoggerFactory.getLogger(PokemonInteractorImp::class.java.simpleName)

    override fun getPokemon(pokemon: String): Observable<PokemonResult> {
        return pokemonRepositoryImp.getPokemon(pokemon)
            .doOnNext { response ->
                run {
                    response.image = Constant.POKEMON_IMAGE_URL + response.id.toString() + ".png"
                }
            }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }

    override fun getPokemonSpecies(pokemon: String): Observable<PokemonSpeciesResult> {
        return pokemonRepositoryImp.getPokemonSpecies(pokemon)
                .doOnComplete { logger.debug("Species service complete") }
                .onErrorReturn { error ->
                    logger.error(error.message)
                    null
                }
    }
}