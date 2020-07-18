package mx.dev1.pokedex.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.dev1.pokedex.R
import java.util.concurrent.TimeUnit

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var navController: NavController
    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.splash_fragment, container, false)

        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        val observer: Observer<Any?> = object : Observer<Any?> {
            override fun onComplete() {
                navController.navigate(R.id.action_splashFragment_to_dashboardFragment)
            }

            override fun onSubscribe(d: Disposable) { /* Do nothing */ }
            override fun onNext(t: Any) { /* Do nothing */ }
            override fun onError(e: Throwable) { /* Do nothing */ }
        }

        Observable.timer(3, TimeUnit.SECONDS, Schedulers.io()).subscribe(observer)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

}