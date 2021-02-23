package mx.dev1.pokedex.presentation.pokemon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.BitmapPalette.Swatch.RGB
import com.github.florent37.glidepalette.GlidePalette
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.PokemonStat
import mx.dev1.pokedex.core.domain.results.PokemonResult
import mx.dev1.pokedex.core.domain.results.PokemonSpeciesResult
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.android.ext.android.inject
import java.util.*

class PokemonFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var toolbar: Toolbar
    private val dependencies: ApiDependencies by inject()
    private lateinit var pokemon: PokemonResult
    private lateinit var pokemonSpeciesResult: PokemonSpeciesResult

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
                val bundle = bundleOf("pokedex" to arguments?.getString("pokedex"))
                Navigation.findNavController(requireView()).navigate(R.id.action_pokemonFragment_to_pokedexInfoFragment, bundle)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        viewModel.dependencies = dependencies
        viewModel.pokemonResult.observe(viewLifecycleOwner, Observer {
            pokemon = it
        })

        viewModel.pokemonSpeciesResult.observe(viewLifecycleOwner, Observer {
            pokemonSpeciesResult = it
            fetchInfoIntoUI()
        })

        viewModel.getPokemon(arguments?.getString("pokemon")!!)
    }

    private fun fetchInfoIntoUI() {
        var pokemonImageView: ImageView = requireView().findViewById(R.id.iv_pokemon_image)
        var pokemonImageContainer: ConstraintLayout = requireView().findViewById(R.id.iv_pokemon_image_container)
        var pokemonWeightTxt: TextView = requireView().findViewById(R.id.txt_pokemon_weight)
        var pokemonHeightTxt: TextView = requireView().findViewById(R.id.txt_pokemon_height)
        var pokemonDescription: TextView = requireView().findViewById(R.id.txt_pokemon_description)
        var pokemonHPTxt: TextView = requireView().findViewById(R.id.txt_pokemon_hp)
        var pokemonATKTxt: TextView = requireView().findViewById(R.id.txt_pokemon_atk)
        var pokemonDEFTxt: TextView = requireView().findViewById(R.id.txt_pokemon_def)
        var pokemonSPDTxt: TextView = requireView().findViewById(R.id.txt_pokemon_spd)
        var pokemonEXPTxt: TextView = requireView().findViewById(R.id.txt_pokemon_exp)

        var pokemonHPProgressBar: ProgressBar = requireView().findViewById(R.id.pb_pokemon_hp)
        var pokemonATKProgressBar: ProgressBar = requireView().findViewById(R.id.pb_pokemon_atk)
        var pokemonDEFProgressBar: ProgressBar = requireView().findViewById(R.id.pb_pokemon_def)
        var pokemonSPDProgressBar: ProgressBar = requireView().findViewById(R.id.pb_pokemon_spd)
        var pokemonEXPProgressBar: ProgressBar = requireView().findViewById(R.id.pb_pokemon_exp)

        Glide.with(requireView()).load(pokemon.image)
            .listener(
                GlidePalette.with(pokemon.image)
                .use(BitmapPalette.Profile.VIBRANT_LIGHT)
                .intoBackground(pokemonImageContainer, RGB)
                .crossfade(true)
            ).into(pokemonImageView)

        pokemonHeightTxt.text = String.format("%.1f m", pokemon.height.toDouble() / 10)
        pokemonWeightTxt.text = String.format("%.1f kg", pokemon.weight.toDouble() / 10)
        pokemonDescription.text = String.format("%s", pokemonSpeciesResult.flavorTextEntries[0].flavor_text)

        for(pokemonStat: PokemonStat in pokemon.stats) {
            when(pokemonStat.stat.name) {
                "hp" -> pokemonHPProgressBar.progress = pokemonStat.baseStat
                "attack" -> pokemonATKProgressBar.progress = pokemonStat.baseStat
                "defense" -> pokemonDEFProgressBar.progress = pokemonStat.baseStat
                "special-defense" -> pokemonSPDProgressBar.progress = pokemonStat.baseStat
            }
        }

        pokemonEXPProgressBar.progress = pokemon.baseExperience.toInt()

        pokemonHPTxt.text = String.format("%d / %d", pokemonHPProgressBar.progress, pokemonHPProgressBar.max)
        pokemonATKTxt.text = String.format("%d / %d", pokemonATKProgressBar.progress, pokemonATKProgressBar.max)
        pokemonDEFTxt.text = String.format("%d / %d", pokemonDEFProgressBar.progress, pokemonDEFProgressBar.max)
        pokemonSPDTxt.text = String.format("%d / %d", pokemonSPDProgressBar.progress, pokemonSPDProgressBar.max)
        pokemonEXPTxt.text = String.format("%d / %d", pokemonEXPProgressBar.progress, pokemonEXPProgressBar.max)
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