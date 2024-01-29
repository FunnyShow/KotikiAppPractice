package dev.keikem.kotikiapp.domain.usecases

import dev.keikem.kotikiapp.data.local.entity.LocalCounter
import dev.keikem.kotikiapp.data.repository.CounterRepository

//Класс, репрезентующии получение данных с базы и преобразование в то с чем мы работаем
class GimmeACounterIncreaseUseCase {

    private val counterRepository: CounterRepository = CounterRepository()

    fun gimme(): String {
        var countNum = counterRepository.loadFromLocal()?.toIntOrNull()
        if(countNum == null) {
            counterRepository.saveToLocal(LocalCounter(id = "1", countedNumber = "1"))
            countNum = 1;
        } else {
            countNum += 1
        }
        counterRepository.saveToLocal(LocalCounter(id = "1", countedNumber = countNum.toString()))
        return countNum.toString()
    }
}