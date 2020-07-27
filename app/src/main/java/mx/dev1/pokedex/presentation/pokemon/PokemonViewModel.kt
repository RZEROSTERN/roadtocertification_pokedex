package mx.dev1.pokedex.presentation.pokemon

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.core.domain.results.PokemonResult
import mx.dev1.pokedex.presentation.ApiDependencies

class PokemonViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
    lateinit var dependencies: ApiDependencies
    var pokemonResult: MutableLiveData<PokemonResult> = MutableLiveData()

    fun getPokemon(pokemon: String) {
        compositeDisposable.add(dependencies.getPokemon(pokemon)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {res -> pokemonResult.postValue(res) },
                {t: Throwable -> Log.e(ContentValues.TAG, t.message!!) }
            ))
    }
}