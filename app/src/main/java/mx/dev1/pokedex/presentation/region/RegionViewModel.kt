package mx.dev1.pokedex.presentation.region

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.presentation.ApiDependencies

class RegionViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies

    fun getDetailedRegion(region: String) {
        compositeDisposable.add(dependencies.getDetailedRegion(region)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> Log.d(ContentValues.TAG, res.pokedexes.size.toString())},
                {t: Throwable? -> Log.e(ContentValues.TAG, t!!.message) }
            )
        )
    }
}