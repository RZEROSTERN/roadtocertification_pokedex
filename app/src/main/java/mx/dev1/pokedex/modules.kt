package mx.dev1.pokedex

import mx.dev1.pokedex.core.data.repositories.PokedexRepositoryImp
import mx.dev1.pokedex.core.data.repositories.PokemonRepositoryImp
import mx.dev1.pokedex.core.data.repositories.RegionRepositoryImp
import mx.dev1.pokedex.core.interactors.*
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.dsl.module

val appModule = module(override = true) {
    single<RegionInteractor> { RegionInteractorImp(RegionRepositoryImp())}
    single<PokedexInteractor> { PokedexInteractorImp(PokedexRepositoryImp(), PokemonRepositoryImp()) }
    single<PokemonInteractor> { PokemonInteractorImp(PokemonRepositoryImp())}

    factory { ApiDependencies(get(), get(), get()) }
}