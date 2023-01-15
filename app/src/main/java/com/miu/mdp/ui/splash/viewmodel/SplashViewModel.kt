package com.miu.mdp.ui.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miu.mdp.domain.repository.UserRepository
import com.miu.mdp.ui.splash.state.SplashUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _splashUIState = MutableStateFlow<SplashUIState>(SplashUIState.Empty)
    val splashUIState: StateFlow<SplashUIState> = _splashUIState

    fun checkOnboardingDone() = viewModelScope.launch {
        val isOnboardingDone = userRepository.isOnBoardingDone()
        if (isOnboardingDone) {
            _splashUIState.value = SplashUIState.OnBoardingDone
        } else {
            _splashUIState.value = SplashUIState.Empty
        }
    }
}