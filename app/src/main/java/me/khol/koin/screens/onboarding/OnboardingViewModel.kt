package me.khol.koin.screens.onboarding

import androidx.lifecycle.ViewModel
import me.khol.koin.repository.OnboardingRepository

class OnboardingViewModel(
    private val repository: OnboardingRepository
) : ViewModel() {

    fun observeNavigation() = repository.observeNavigation()

    fun observeStep() = repository.observeStep()

    fun stepBack() = repository.previousStep()
}