package me.khol.koin.screens.onboarding.step.two

import androidx.lifecycle.ViewModel
import me.khol.koin.di.Scopes
import me.khol.koin.repository.OnboardingRepository
import org.koin.standalone.StandAloneContext

class TwoViewModel(
    private val repository: OnboardingRepository
) : ViewModel() {

    fun continueToNextStep() {
        StandAloneContext.getKoin().koinContext.createScope(Scopes.SIGNED_IN)
        repository.nextStep()
    }
}