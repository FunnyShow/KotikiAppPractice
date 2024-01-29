package dev.keikem.kotikiapp.data.repository

import dev.keikem.kotikiapp.DatabaseHolder
import dev.keikem.kotikiapp.data.local.entity.LocalCounter

class CounterRepository {

    private val database = DatabaseHolder.provideDb()
    fun loadFromLocal(): String? = database?.counterDao()?.get()?.countedNumber

    fun saveToLocal(counter: LocalCounter) {
        database?.counterDao()?.set(counter)
    }
}