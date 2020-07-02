package mx.dev1.pokedex.presentation.dashboard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.dev1.pokedex.R
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.android.ext.android.inject

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private val dependencies: ApiDependencies by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        viewModel.dependencies = this.dependencies
        viewModel.getRegions()
        viewModel.getDetailedRegion("alola")
    }

}