package mx.dev1.pokedex.presentation.pokemon

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class PokemonViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()
}