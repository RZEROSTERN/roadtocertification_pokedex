package mx.dev1.pokedex.presentation.region

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import mx.dev1.pokedex.R
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.android.ext.android.inject

class RegionFragment : Fragment() {
    private lateinit var viewModel: RegionViewModel
    private lateinit var toolbar: Toolbar
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

}