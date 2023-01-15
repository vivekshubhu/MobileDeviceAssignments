package com.miu.mdp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quiz")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val answer: String,
    val options: List<String>
)