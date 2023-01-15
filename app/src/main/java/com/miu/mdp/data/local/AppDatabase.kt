package com.miu.mdp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miu.mdp.data.local.convertors.Converters
import com.miu.mdp.data.local.dao.QuizDAO
import com.miu.mdp.data.local.entity.QuizEntity

@Database(entities = [QuizEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizDAO(): QuizDAO

    companion object {
        const val DATABASE_NAME = "quiz_db"
    }
}