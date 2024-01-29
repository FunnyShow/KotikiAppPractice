package dev.keikem.kotikiapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Сущность таблицы, репрезентующей способ хранения котиков
@Entity(tableName = "cat_table")
data class LocalCat(
    @PrimaryKey val id: String,
    @ColumnInfo("image_url") val imageUrl: String
)