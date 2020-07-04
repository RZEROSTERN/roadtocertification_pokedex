package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.repositories.PokedexRepositoryImp
import mx.dev1.pokedex.core.domain.results.PokedexResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PokedexInteractorImp(private val pokedexRepositoryImp: PokedexRepositoryImp): PokedexInteractor {
    val logger: Logger = LoggerFactory.getLogger(PokedexInteractorImp::class.java.simpleName)

    override fun getPokedex(pokedex: String): Observable<PokedexResult> {
        return pokedexRepositoryImp.getPokedex(pokedex)
            .doOnNext { response -> logger.debug(response.toString()) }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }
}