package mx.dev1.pokedex.presentation.region

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokedex
import mx.dev1.pokedex.presentation.ApiDependencies
import mx.dev1.pokedex.presentation.region.adapters.PokedexesAdapter
import org.koin.android.ext.android.inject

class RegionFragment : Fragment() {
    private val TAG = RegionFragment::class.java.simpleName
    private lateinit var viewModel: RegionViewModel
    private lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var items: MutableList<Pokedex>
    private val dependencies: ApiDependencies by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.region_fragment, container, false)

        var activity = requireActivity() as AppCompatActivity
        toolbar = view.findViewById(R.id.tb_region)
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar!!.title = arguments?.getString("region")!!.capitalize()
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegionViewModel::class.java)
        viewModel.dependencies = dependencies

        viewModel.regionPokedexes.observe(viewLifecycleOwner, Observer {
            items = it
            initRecyclerView()
        })
        viewModel.getDetailedRegion(arguments?.getString("region")!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Navigation.findNavController(requireView()).navigate(R.id.action_regionFragment_to_dashboardFragment)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDetach() {
        viewModel.compositeDisposable.dispose()
        super.onDetach()
    }

    override fun onDestroy() {
        viewModel.compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun initRecyclerView() {
        val listener = View.OnClickListener() {
            val bundle = bundleOf("pokedex" to it
                .findViewById<TextView>(R.id.txt_pokedex_title)
                .text.toString().replace(" ", "-").toLowerCase(),
            "region" to arguments?.getString("region"))

            Navigation.findNavController(requireView())
                .navigate(R.id.action_regionFragment_to_pokedexInfoFragment, bundle)
        }

        recyclerView = requireView().findViewById(R.id.rv_region_pokedexes)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = PokedexesAdapter(items, listener)
    }

}