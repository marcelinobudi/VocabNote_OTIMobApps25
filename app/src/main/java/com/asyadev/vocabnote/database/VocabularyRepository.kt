package com.asyadev.vocabnote.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class VocabularyRepository(private val vocabularyDao: VocabularyDao) {
    @WorkerThread
    suspend fun getVocabulary() = vocabularyDao.getAll()

    @WorkerThread
    suspend fun insert(vocabulary: Vocabulary){
        vocabularyDao.insertAll(vocabulary)
    }

    @WorkerThread
    suspend fun update(vocabulary: Vocabulary){
        vocabularyDao.update(vocabulary)
    }

    @WorkerThread
    suspend fun delete(vocabulary: Vocabulary){
        vocabularyDao.delete(vocabulary)
    }
}