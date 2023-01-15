package com.miu.mdp.ui.result

import com.miu.mdp.domain.model.Quiz

data class ResultModel(
    val quiz: Quiz,
    val yourAnswer: String,
    val correctAnswer: String
)