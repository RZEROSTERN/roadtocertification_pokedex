package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.core.data.repositories.PokedexRepositoryImp
import mx.dev1.pokedex.core.data.repositories.PokemonRepositoryImp
import mx.dev1.pokedex.core.domain.Pokemon
import mx.dev1.pokedex.core.domain.results.PokedexResult
import mx.dev1.pokedex.core.utils.Constant
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PokedexInteractorImp(private val pokedexRepositoryImp: PokedexRepositoryImp,
        private val pokemonRepositoryImp: PokemonRepositoryImp): PokedexInteractor {
    val logger: Logger = LoggerFactory.getLogger(PokedexInteractorImp::class.java.simpleName)

    override fun getPokedex(pokedex: String): Observable<PokedexResult> {
        return pokedexRepositoryImp.getPokedex(pokedex)
            .doOnNext { response ->
                run {
                    for(item: Pokemon in response.pokemonEntries) {
                        item.pokemonImage = Constant.POKEMON_IMAGE_URL + item.entryNumber.toString() + ".png"
                        pokemonRepositoryImp.getPokemon(item.pokemonSpecies.name)
                            .subscribeOn(Schedulers.io())
                            .subscribe({response2 -> item.pokemonDetails = response2}, {t -> logger.error(t.message)})
                    }
                }
            }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }
}