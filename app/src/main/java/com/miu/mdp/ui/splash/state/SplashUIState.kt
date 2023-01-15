package com.miu.mdp.ui.splash.state

sealed class SplashUIState {
    object Empty : SplashUIState()
    object OnBoardingDone : SplashUIState()
}