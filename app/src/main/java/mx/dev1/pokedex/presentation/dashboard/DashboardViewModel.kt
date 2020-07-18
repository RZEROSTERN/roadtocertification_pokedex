package mx.dev1.pokedex.presentation.dashboard

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.core.domain.Region
import mx.dev1.pokedex.presentation.ApiDependencies

class DashboardViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    var regions: MutableLiveData<MutableList<Region>> = MutableLiveData()
    lateinit var dependencies: ApiDependencies

    fun getRegions() {
        compositeDisposable.add(dependencies.getRegions()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> regions.postValue(res.results)},
                {t: Throwable -> Log.e(TAG, t.message!!) }
            )
        )
    }
}