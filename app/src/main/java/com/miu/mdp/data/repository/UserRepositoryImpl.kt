package com.miu.mdp.data.repository

import com.miu.mdp.data.local.PreferenceManager
import com.miu.mdp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : UserRepository {
    override fun isOnBoardingDone(): Boolean {
        return preferenceManager.getBoolean(PreferenceManager.KEY_ON_BOARDING_DONE)
    }

    override fun saveOnBoardingDone(value: Boolean) {
        preferenceManager.saveBoolean(PreferenceManager.KEY_ON_BOARDING_DONE, value)
    }
}