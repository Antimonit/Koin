package me.khol.koin.screens.onboarding.step.two

import androidx.lifecycle.ViewModel
import me.khol.koin.repository.OnboardingRepository

class TwoViewModel(
    private val repository: OnboardingRepository
) : ViewModel() {

    fun continueToNextStep() {
        repository.nextStep()
    }
}