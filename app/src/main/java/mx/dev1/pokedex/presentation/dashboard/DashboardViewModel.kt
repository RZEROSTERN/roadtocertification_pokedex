package mx.dev1.pokedex.presentation.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.presentation.ApiDependencies

class DashboardViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies

    fun getRegions() {
        compositeDisposable.add(dependencies.getRegions()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> Log.d(TAG, res.count.toString())},
                {t: Throwable? -> Log.e(TAG, t!!.message) }
            )
        )
    }

    fun getDetailedRegion(region: String) {
        compositeDisposable.add(dependencies.getDetailedRegion(region)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> Log.d(TAG, res.pokedexes.size.toString())},
                {t: Throwable? -> Log.e(TAG, t!!.message) }
            )
        )
    }
}