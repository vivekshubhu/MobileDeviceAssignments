package com.miu.mdp.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.mdp.domain.model.Quiz
import com.miu.mdp.domain.repository.QuizRepository
import com.miu.mdp.ui.result.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    var allQuestionList = mutableListOf<Quiz>()
    var currentQuestionNumber: Int = 1
    var totalQuestionToAnswer: Long = 0

    private val _currentQuiz = MutableLiveData<Quiz>()
    val currentQuiz: LiveData<Quiz> = _currentQuiz

    val correctAnswerIndexList = mutableListOf<Int>()
    val incorrectAnswerIndexList = mutableListOf<Map<Int, String>>()

    private val _resetLiveData = MutableLiveData(false)
    val resetLiveData: LiveData<Boolean> = _resetLiveData

    init {
        fetchQuestions()
    }

    private fun fetchQuestions() {
        viewModelScope.launch(Dispatchers.Main) {
            allQuestionList = repository.getQuizList().toMutableList()
            allQuestionList.shuffle()
            totalQuestionToAnswer = allQuestionList.size.toLong()
            loadQuestion(currentQuestionNumber)
        }
    }


    private fun loadQuestion(questionNumber: Int) {
        // Load Question
        val quiz = allQuestionList[questionNumber - 1]
        _currentQuiz.value = quiz

        // Question Loaded. Set Can answer
        currentQuestionNumber = questionNumber
    }

    fun loadNextQuestion() {
        currentQuestionNumber++
        loadQuestion(currentQuestionNumber)
    }

    fun getCorrectAnswer(selectedAnswer: String): String {
        //Check answer
        if (allQuestionList[currentQuestionNumber - 1].answer == selectedAnswer) {
            // Correct answer
            correctAnswerIndexList.add(currentQuestionNumber - 1)
        } else {
            // Incorrect answer
            incorrectAnswerIndexList.add(mapOf(currentQuestionNumber - 1 to selectedAnswer))
        }

        return allQuestionList[currentQuestionNumber - 1].answer
    }

    fun getResultList(): List<ResultModel> {
        val resultList = mutableListOf<ResultModel>()
        for (i in 0 until allQuestionList.size) {
            val quiz = allQuestionList[i]
            if (correctAnswerIndexList.contains(i)) {
                val correctAnswer = quiz.options[getIndexFromAnswer(quiz.answer)]
                resultList.add(ResultModel(quiz, correctAnswer, correctAnswer))
            } else {
                val incorrectAnswerIndex =
                    incorrectAnswerIndexList.find { it.containsKey(i) }?.get(i)
                val incorrectAnswer = quiz.options[getIndexFromAnswer(incorrectAnswerIndex ?: "")]
                val correctAnswer = quiz.options[getIndexFromAnswer(quiz.answer)]
                resultList.add(ResultModel(quiz, incorrectAnswer, correctAnswer))
            }
        }
        return resultList
    }

    private fun getIndexFromAnswer(answer: String): Int {
        return when (answer) {
            "a" -> 0
            "b" -> 1
            "c" -> 2
            "d" -> 3
            else -> 0
        }
    }


    fun resetQuiz() {
        currentQuestionNumber = 1
        correctAnswerIndexList.clear()
        incorrectAnswerIndexList.clear()
        loadQuestion(currentQuestionNumber)
        _resetLiveData.value = _resetLiveData.value?.not()
    }
}