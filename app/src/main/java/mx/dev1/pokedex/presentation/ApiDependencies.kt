package mx.dev1.pokedex.presentation

import mx.dev1.pokedex.core.interactors.RegionInteractor

class ApiDependencies(var regionInteractor: RegionInteractor) {
    fun getRegions() = regionInteractor.getRegions()
    fun getDetailedRegion(region: String) = regionInteractor.getDetailedRegion(region)
}