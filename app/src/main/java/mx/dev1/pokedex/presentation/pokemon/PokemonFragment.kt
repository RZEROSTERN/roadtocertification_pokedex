package mx.dev1.pokedex.presentation.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import mx.dev1.pokedex.R
import java.util.*

class PokemonFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.pokemon_fragment, container, false)

        val activity = requireActivity() as AppCompatActivity
        toolbar = view.findViewById(R.id.tb_pokemon)
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar!!.title = arguments?.getString("pokemon")!!
            .capitalize(Locale.getDefault())
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                var bundle = bundleOf("pokedex" to arguments?.getString("pokedex"))
                Navigation.findNavController(requireView()).navigate(R.id.action_pokemonFragment_to_pokedexInfoFragment, bundle)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDetach() {
        viewModel.compositeDisposable.dispose()
        super.onDetach()
    }

    override fun onDestroy() {
        viewModel.compositeDisposable.dispose()
        super.onDestroy()
    }

}