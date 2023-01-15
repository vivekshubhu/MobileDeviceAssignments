package com.miu.mdp.data.repository
import com.miu.mdp.data.local.dao.QuizDAO
import com.miu.mdp.data.local.mock.defaultQuizQuestions
import com.miu.mdp.data.mapper.toEntity
import com.miu.mdp.data.mapper.toQuiz
import com.miu.mdp.domain.model.Quiz
import com.miu.mdp.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizDAO: QuizDAO
) : QuizRepository {
    override suspend fun getQuizById(id: Int): Quiz? {
        return quizDAO.getQuizById(id)?.toQuiz()
    }

    override suspend fun getQuizList(): List<Quiz> {
        if(quizDAO.getQuizList().isEmpty()) {
            quizDAO.insert(defaultQuizQuestions());
        }
        return quizDAO.getQuizList().map { it.toQuiz() }
    }

    override suspend fun saveQuiz(quiz: Quiz) {
        quizDAO.insert(quiz.toEntity())
    }
}