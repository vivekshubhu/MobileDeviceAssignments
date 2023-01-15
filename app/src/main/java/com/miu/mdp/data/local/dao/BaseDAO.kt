package com.miu.mdp.data.local.dao

import androidx.room.*

interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<T>): List<Long>

    @Update
    suspend fun update(obj: T): Int

    @Update
    suspend fun update(obj: List<T>): Int

    @Delete
    suspend fun delete(obj: T): Int

    @Delete
    suspend fun delete(obj: List<T>): Int
}