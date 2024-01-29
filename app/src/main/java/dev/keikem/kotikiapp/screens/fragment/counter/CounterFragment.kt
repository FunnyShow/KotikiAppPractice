package dev.keikem.kotikiapp.screens.fragment.counter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.progressindicator.CircularProgressIndicator
import dev.keikem.kotikiapp.R

class CounterFragment : Fragment(R.layout.fragment_counter), LifecycleEventObserver {

    private var viewModel: CounterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvCounter = view.findViewById<TextView>(R.id.tv_counted)
        val button = view.findViewById<Button>(R.id.button)
        val buttonIncrease = view.findViewById<Button>(R.id.buttonIncrease)
        val progress = view.findViewById<CircularProgressIndicator>(R.id.progress)

        viewModel?.counted?.observe(viewLifecycleOwner) { num ->
            if (num.isNotEmpty()) {
                tvCounter.text = num
                tvCounter.isVisible = true
                progress.isVisible = false
                viewModel?.loadFromLocal()
            }
        }

        buttonIncrease.text = "+"
        button.text = "Назад"

        buttonIncrease.setOnClickListener {
            tvCounter.isVisible = false;
            progress.isVisible = true
            viewModel?.loadFromLocalAndIncrease()
        }

        button.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.appNavHostFragment).popBackStack()
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel = ViewModelProvider(this)[CounterViewModel::class.java]
            }

            else -> Unit
        }
    }
}