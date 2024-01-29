package dev.keikem.kotikiapp.screens.fragment.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.keikem.kotikiapp.domain.usecases.GimmeADogLocalUseCase
import dev.keikem.kotikiapp.domain.usecases.GimmeADogRemoteUseCase

class DogViewModel : ViewModel() {

    private val gimmeADogRemoteUseCase: GimmeADogRemoteUseCase by lazy { GimmeADogRemoteUseCase() }
    private val gimmeADogLocalUseCase: GimmeADogLocalUseCase by lazy { GimmeADogLocalUseCase() }


    private var _imageUrl: MutableLiveData<String> = MutableLiveData("")
    val imageUrl: LiveData<String> = _imageUrl

    init {
        loadFromLocal()
    }

    private fun loadFromLocal() {
        Thread {
            val im = gimmeADogLocalUseCase.gimme()
            im?.let { image -> _imageUrl.postValue(image) }
        }.start()
    }

    fun loadFromRemote() {
        //Thread - отдельный поток выполнения, он отвечает за то где будет выполнятся операция
        Thread {
            Thread.sleep(5000)
            _imageUrl.postValue(gimmeADogRemoteUseCase.gimme())
        }.start()
    }

}