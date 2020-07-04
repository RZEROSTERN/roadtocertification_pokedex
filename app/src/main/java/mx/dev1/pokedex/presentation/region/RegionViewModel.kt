package mx.dev1.pokedex.presentation.region

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.core.domain.Pokedex
import mx.dev1.pokedex.presentation.ApiDependencies

class RegionViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies
    var regionPokedexes: MutableLiveData<MutableList<Pokedex>> = MutableLiveData()

    fun getDetailedRegion(region: String) {
        compositeDisposable.add(dependencies.getDetailedRegion(region)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> regionPokedexes.postValue(res.pokedexes) },
                {t: Throwable? -> Log.e(ContentValues.TAG, t!!.message) }
            )
        )
    }
}