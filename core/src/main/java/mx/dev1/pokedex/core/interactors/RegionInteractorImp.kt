package mx.dev1.pokedex.core.interactors

import io.reactivex.Observable
import mx.dev1.pokedex.core.data.entities.results.RegionDetailedResult
import mx.dev1.pokedex.core.data.entities.results.RegionResult
import mx.dev1.pokedex.core.data.repositories.RegionRepositoryImp
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RegionInteractorImp(private val regionRepositoryImp: RegionRepositoryImp): RegionInteractor {
    val logger: Logger = LoggerFactory.getLogger(RegionInteractorImp::class.java.simpleName)

    override fun getRegions(): Observable<RegionResult> {
        return regionRepositoryImp.getRegions()
            .doOnNext { response -> logger.debug(response.toString()) }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }

    override fun getDetailedRegion(region: String): Observable<RegionDetailedResult> {
        return regionRepositoryImp.getRegionByName(region)
            .doOnNext { response -> logger.debug(response.toString()) }
            .doOnComplete { logger.debug("Service complete") }
            .onErrorReturn { error ->
                logger.error(error.message)
                null
            }
    }
}