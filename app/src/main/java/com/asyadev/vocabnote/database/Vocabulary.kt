package com.asyadev.vocabnote.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vocabulary(
    @ColumnInfo(name="word") val word: String,
    @ColumnInfo(name="translation") val translation: String,
    @ColumnInfo(name="description") val description: String,
    @ColumnInfo(name="example") val example: String,
    @ColumnInfo(name = "pronounciation") val pronounciation: String,
    @ColumnInfo(name="difficulty") val difficulty: String,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)
