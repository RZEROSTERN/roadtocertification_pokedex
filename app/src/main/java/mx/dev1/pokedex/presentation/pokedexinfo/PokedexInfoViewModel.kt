package mx.dev1.pokedex.presentation.pokedexinfo

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.core.domain.Pokemon
import mx.dev1.pokedex.presentation.ApiDependencies

class PokedexInfoViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies
    var pokemon: MutableLiveData<MutableList<Pokemon>> = MutableLiveData()

    fun getPokedex(pokedex: String) {
        compositeDisposable.add(dependencies.getPokedex(pokedex)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> pokemon.postValue(res.pokemonEntries) },
                {t: Throwable -> Log.e(ContentValues.TAG, t.message!!) }
            )
        )
    }
}