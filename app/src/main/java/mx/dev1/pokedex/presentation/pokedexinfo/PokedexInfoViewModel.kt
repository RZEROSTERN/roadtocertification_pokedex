package mx.dev1.pokedex.presentation.pokedexinfo

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.presentation.ApiDependencies

class PokedexInfoViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies

    fun getPokedex(pokedex: String) {
        compositeDisposable.add(dependencies.getPokedex(pokedex)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> Log.d(ContentValues.TAG, res.pokemonEntries.size.toString()) },
                {t: Throwable? -> Log.e(ContentValues.TAG, t!!.message) }
            )
        )
    }
}