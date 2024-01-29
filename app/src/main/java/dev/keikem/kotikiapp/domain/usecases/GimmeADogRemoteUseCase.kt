package dev.keikem.kotikiapp.domain.usecases

import dev.keikem.kotikiapp.data.local.entity.LocalDog
import dev.keikem.kotikiapp.data.repository.DogRepository

class GimmeADogRemoteUseCase {

    private val repository: DogRepository = DogRepository()

    fun gimme(): String {
        val dogUrl = repository.loadFromRemote()
        repository.saveToLocal(LocalDog(id = "1", imageUrl = dogUrl))

        return dogUrl
    }
}