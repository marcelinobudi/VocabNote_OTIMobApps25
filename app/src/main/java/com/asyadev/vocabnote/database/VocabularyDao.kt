package com.asyadev.vocabnote.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY uid ASC")
    fun getAll(): Flow<List<Vocabulary>>

//    @Query("SELECT * FROM vocabulary WHERE uid IN (:wordIds)")
//    fun loadAllByIds(wordIds: IntArray): Flow<List<Vocabulary>>
//
//    @Query("SELECT * FROM vocabulary WHERE word LIKE :word")
//    fun findByWord(word: String): Vocabulary

    @Update
    suspend fun update(vararg vocabulary: Vocabulary)

    @Insert
    suspend fun insertAll(vararg vocabulary: Vocabulary)

    @Delete
    suspend fun delete(user: Vocabulary)
}