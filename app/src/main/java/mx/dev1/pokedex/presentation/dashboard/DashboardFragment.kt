package mx.dev1.pokedex.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.presentation.ApiDependencies
import org.koin.android.ext.android.inject

class DashboardFragment : Fragment() {

    private val TAG = DashboardFragment::class.java.simpleName
    private lateinit var viewModel: DashboardViewModel
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private val dependencies: ApiDependencies by inject()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do Nothing
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.dashboard_fragment, container, false)

        var activity = requireActivity() as AppCompatActivity
        toolbar = view.findViewById(R.id.tb_dashboard)
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar!!.title = "Dashboard"
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.dependencies = this.dependencies

        initDrawerLayout(savedInstanceState)

        var item = navigationView.menu.getItem(0)

        viewModel.regions.observe(viewLifecycleOwner, Observer {
            it.forEach { itr -> item.subMenu.add(R.id.regions_group,
                Menu.NONE, Menu.NONE, itr.name.capitalize()) }
        })

        viewModel.getRegions()
    }

    private fun initDrawerLayout(savedInstanceState: Bundle?) {
        drawerLayout = requireView().findViewById(R.id.dl_dashboard)
        drawerToggle = setupDrawerToggle()

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        drawerLayout.addDrawerListener(drawerToggle)

        navigationView = requireView().findViewById(R.id.nvView)
        navigationView.setNavigationItemSelectedListener { item ->
            selectDrawerItem(item)
            true
        }
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(
            requireActivity(),
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    private fun selectDrawerItem(item: MenuItem) {
        when(item.itemId) {
            else -> {
                Log.d(TAG, item.title.toString().toLowerCase())
            }
        }
    }
}