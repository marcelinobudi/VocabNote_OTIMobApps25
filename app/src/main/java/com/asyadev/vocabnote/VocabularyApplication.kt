package com.asyadev.vocabnote

import android.app.Application
import com.asyadev.vocabnote.database.AppDatabase
import com.asyadev.vocabnote.database.VocabularyRepository

class VocabularyApplication: Application() {
    private val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { VocabularyRepository(database.vocabularyDao()) }
}