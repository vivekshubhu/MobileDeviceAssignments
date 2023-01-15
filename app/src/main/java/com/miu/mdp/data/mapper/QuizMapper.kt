package com.miu.mdp.data.mapper

import com.miu.mdp.data.local.entity.QuizEntity
import com.miu.mdp.domain.model.Quiz

fun QuizEntity.toQuiz(): Quiz {
    return Quiz(
        id = id,
        question = question,
        answer = answer,
        options = options
    )
}

fun Quiz.toEntity() = QuizEntity(
    id = id,
    question = question,
    answer = answer,
    options = options
)