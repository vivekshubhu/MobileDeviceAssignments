package com.miu.mdp.domain.repository

import com.miu.mdp.domain.model.Quiz

interface QuizRepository {
    suspend fun getQuizById(id: Int): Quiz?
    suspend fun getQuizList(): List<Quiz>
    suspend fun saveQuiz(quiz: Quiz)
}