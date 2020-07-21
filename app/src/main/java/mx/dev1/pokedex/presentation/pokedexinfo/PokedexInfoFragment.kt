package mx.dev1.pokedex.presentation.pokedexinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokemon
import mx.dev1.pokedex.presentation.ApiDependencies
import mx.dev1.pokedex.presentation.pokedexinfo.adapters.PokedexInfoAdapter
import org.koin.android.ext.android.inject
import java.util.*

class PokedexInfoFragment : Fragment() {

    private lateinit var viewModel: PokedexInfoViewModel
    private lateinit var toolbar: Toolbar
    private val dependencies: ApiDependencies by inject()
    lateinit var items: MutableList<Pokemon>
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.pokedex_info_fragment, container, false)

        val activity = requireActivity() as AppCompatActivity
        toolbar = view.findViewById(R.id.tb_pokedex_info)
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar!!.title = arguments?.getString("pokedex")!!
            .capitalize(Locale.getDefault()).replace("-", " ") + "'s Pokedex"
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                var bundle = bundleOf("region" to arguments?.getString("region"))
                Navigation.findNavController(requireView()).navigate(R.id.action_pokedexInfoFragment_to_regionFragment, bundle)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokedexInfoViewModel::class.java)

        viewModel.dependencies = dependencies
        viewModel.pokemon.observe(viewLifecycleOwner, Observer {
            items = it
            initRecyclerView()
        })
        viewModel.getPokedex(arguments?.getString("pokedex")!!)
    }

    private fun initRecyclerView() {
        recyclerView = requireView().findViewById(R.id.rv_pokedex_info)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = PokedexInfoAdapter(items)
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