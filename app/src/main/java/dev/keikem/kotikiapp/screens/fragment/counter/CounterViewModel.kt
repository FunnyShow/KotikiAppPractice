package dev.keikem.kotikiapp.screens.fragment.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.keikem.kotikiapp.domain.usecases.GimmeACounterIncreaseUseCase
import dev.keikem.kotikiapp.domain.usecases.GimmeACounterLocalUseCase

class CounterViewModel : ViewModel() {

    private val gimmeACounterLocalUseCase: GimmeACounterLocalUseCase by lazy { GimmeACounterLocalUseCase() }
    private val gimmeACounterIncreaseUseCase: GimmeACounterIncreaseUseCase by lazy { GimmeACounterIncreaseUseCase() }

    private var _counted: MutableLiveData<String> = MutableLiveData("")
    val counted: LiveData<String> = _counted

    init {
        loadFromLocal()
    }

    fun loadFromLocal() {
        Thread {
            val im = gimmeACounterLocalUseCase.gimme()
            im?.let { counted -> _counted.postValue(counted) }
        }.start()
    }

    fun loadFromLocalAndIncrease() {
        //Thread - отдельный поток выполнения, он отвечает за то где будет выполнятся операция
        Thread {
            Thread.sleep(5000)
            _counted.postValue(gimmeACounterIncreaseUseCase.gimme())
        }.start()
    }

}