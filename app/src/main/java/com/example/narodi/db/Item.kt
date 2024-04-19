package com.example.narodi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @ColumnInfo(name = "nameN")      // название народа
        var nameN: String,
        @ColumnInfo(name = "description")       // описание народа
        var description: String,
        @ColumnInfo(name = "avatar_url")        // ссылка на аватар народа
        var avatarUrl: Int,
        @ColumnInfo(name = "favorite")      // добавлен ли в избранное
        var favorite: Int,
    )