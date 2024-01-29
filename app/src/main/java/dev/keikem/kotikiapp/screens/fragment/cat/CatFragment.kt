package dev.keikem.kotikiapp.screens.fragment.cat

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import coil.load
import com.google.android.material.progressindicator.CircularProgressIndicator
import dev.keikem.kotikiapp.R

//Фрагмент, показывающии котика
class CatFragment : Fragment(R.layout.fragment_cat), LifecycleEventObserver {

    private var viewModel: CatViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.image)
        val button = view.findViewById<Button>(R.id.button)
        val buttonLoad = view.findViewById<Button>(R.id.buttonLoad)
        val buttonCounter = view.findViewById<Button>(R.id.buttonCounter)
        val progress = view.findViewById<CircularProgressIndicator>(R.id.progress)

        viewModel?.imageUrl?.observe(viewLifecycleOwner) { url ->
            if (url.isNotEmpty()) {
                image.load(url)
                progress.isVisible = false
            }
        }

        buttonLoad.setOnClickListener {
            progress.isVisible = true
            viewModel?.loadFromRemote()
        }

        button.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.appNavHostFragment)
                .navigate(R.id.toNextFragment)
        }
        buttonCounter.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.appNavHostFragment)
                .navigate(R.id.toCounterFragment)
        }

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel = ViewModelProvider(this)[CatViewModel::class.java]
            }

            else -> Unit
        }
    }

}