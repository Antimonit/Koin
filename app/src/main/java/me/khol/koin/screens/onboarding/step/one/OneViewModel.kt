package me.khol.koin.screens.onboarding.step.one

import androidx.lifecycle.ViewModel
import me.khol.koin.repository.OnboardingRepository

class OneViewModel(
    private val repository: OnboardingRepository
) : ViewModel() {

    fun continueToNextStep() {
        repository.nextStep()
    }
}