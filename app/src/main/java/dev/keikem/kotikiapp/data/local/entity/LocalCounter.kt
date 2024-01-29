package dev.keikem.kotikiapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Сущность таблицы, репрезентующей способ хранения котиков
@Entity(tableName = "counter_table")
data class LocalCounter(
    @PrimaryKey val id: String,
    @ColumnInfo("counted") val countedNumber: String
)