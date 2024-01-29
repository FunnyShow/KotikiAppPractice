package dev.keikem.kotikiapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
data class LocalDog(
    @PrimaryKey val id: String,
    @ColumnInfo("image_url") val imageUrl: String
)