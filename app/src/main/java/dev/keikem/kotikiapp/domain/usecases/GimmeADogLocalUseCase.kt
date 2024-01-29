package dev.keikem.kotikiapp.domain.usecases

import dev.keikem.kotikiapp.data.repository.DogRepository

class GimmeADogLocalUseCase {

    private val repository: DogRepository = DogRepository()

    fun gimme(): String? = repository.loadFromLocal()
}