package dev.keikem.kotikiapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.keikem.kotikiapp.data.local.Database

//Класс, репрезентирующий само приложение
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DatabaseHolder.createDatabase(applicationContext)
    }
}

//Обьект, отвечающии за создание/хранение бд
object DatabaseHolder {

    private var _database: Database? = null

    fun createDatabase(context: Context) {
        if (_database == null) {
            _database = Room.databaseBuilder(
                context,
                Database::class.java,
                "database.db"
            ).build()
        }
    }

    fun provideDb(): Database? = _database

}