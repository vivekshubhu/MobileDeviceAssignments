package com.miu.mdp.ui.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.miu.mdp.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun saveOnboardingDone(value:Boolean) = userRepository.saveOnBoardingDone(value)

}