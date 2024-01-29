package dev.keikem.kotikiapp.screens.fragment.cat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.keikem.kotikiapp.domain.usecases.GimmeACatLocalUseCase
import dev.keikem.kotikiapp.domain.usecases.GimmeACatRemoteUseCase

class CatViewModel : ViewModel() {

    private val gimmeACatRemoteUseCase: GimmeACatRemoteUseCase by lazy { GimmeACatRemoteUseCase() }
    private val gimmeACatLocalUseCase: GimmeACatLocalUseCase by lazy { GimmeACatLocalUseCase() }

    private var _imageUrl: MutableLiveData<String> = MutableLiveData("")
    val imageUrl: LiveData<String> = _imageUrl

    init {
        loadFromDatabase()
    }

    private fun loadFromDatabase() {
        Thread {
            val im = gimmeACatLocalUseCase.gimme()
            im?.let { image -> _imageUrl.postValue(image) }
        }.start()
    }

    fun loadFromRemote() {
        //Thread - отдельный поток выполнения, он отвечает за то где будет выполнятся операция
        Thread {
            Thread.sleep(5000)
            _imageUrl.postValue(gimmeACatRemoteUseCase.gimme())
        }.start()
    }
}