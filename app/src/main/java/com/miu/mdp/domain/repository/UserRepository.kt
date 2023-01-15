package com.miu.mdp.domain.repository

interface UserRepository {
    fun isOnBoardingDone(): Boolean
    fun saveOnBoardingDone(value: Boolean)
}