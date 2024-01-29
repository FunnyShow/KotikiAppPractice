package dev.keikem.kotikiapp.data.remote

// Обьект, в который мы преобразовываем полученную нами информацию о котиках
data class RemoteCat(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)