package dev.keikem.kotikiapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.keikem.kotikiapp.data.local.entity.LocalCat


//Сущность для взаимодействия с таблицей БД котиков
@Dao
interface CatDao {

    @Query("SELECT * from cat_table")
    fun get() : LocalCat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun set(cat: LocalCat)
}