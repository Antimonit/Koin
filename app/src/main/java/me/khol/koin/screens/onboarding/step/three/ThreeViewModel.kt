package me.khol.koin.screens.onboarding.step.three

import androidx.lifecycle.ViewModel
import me.khol.koin.repository.OnboardingRepository

class ThreeViewModel(
    private val onboardingRepository: OnboardingRepository,
    private val userRepository: OnboardingRepository
) : ViewModel() {

    fun continueToNextStep() {
        onboardingRepository.nextStep()
    }
}