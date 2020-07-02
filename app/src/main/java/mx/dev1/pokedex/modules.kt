package mx.dev1.pokedex

import mx.dev1.pokedex.core.data.repositories.RegionRepositoryImp
import mx.dev1.pokedex.core.interactors.RegionInteractor
import mx.dev1.pokedex.core.interactors.RegionInteractorImp
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.dsl.module

val appModule = module(override = true) {
    single<RegionInteractor> { RegionInteractorImp(RegionRepositoryImp())}
    factory { ApiDependencies(get()) }
}