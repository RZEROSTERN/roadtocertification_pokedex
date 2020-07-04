package mx.dev1.pokedex.presentation

import mx.dev1.pokedex.core.interactors.PokedexInteractor
import mx.dev1.pokedex.core.interactors.RegionInteractor

class ApiDependencies(var regionInteractor: RegionInteractor,
                      val pokedexInteractor: PokedexInteractor) {
    fun getRegions() = regionInteractor.getRegions()
    fun getDetailedRegion(region: String) = regionInteractor.getDetailedRegion(region)
    fun getPokedex(pokedex: String) = pokedexInteractor.getPokedex(pokedex)
}