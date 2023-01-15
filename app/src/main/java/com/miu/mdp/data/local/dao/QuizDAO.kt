package com.miu.mdp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.miu.mdp.data.local.entity.QuizEntity

@Dao
interface QuizDAO : BaseDAO<QuizEntity> {

    @Query("SELECT * FROM Quiz WHERE id = :id")
    suspend fun getQuizById(id: Int): QuizEntity?

    @Query("SELECT * FROM Quiz")
    suspend fun getQuizList(): List<QuizEntity>
}