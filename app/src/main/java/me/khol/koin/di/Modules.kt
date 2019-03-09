package me.khol.koin.di

import me.khol.koin.screens.onboarding.OnboardingViewModel
import me.khol.koin.screens.onboarding.step.one.OneViewModel
import me.khol.koin.screens.onboarding.step.one.ThreeViewModel
import me.khol.koin.screens.onboarding.step.two.TwoViewModel
import me.khol.koin.repository.OnboardingRepository
import me.khol.koin.repository.OnboardingRepositoryImpl
import me.khol.koin.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {

    scoped<OnboardingRepository>(Scopes.ONBOARDING) { OnboardingRepositoryImpl() }
}

val viewModelModule = module {

    viewModel { OnboardingViewModel(get()) }

    viewModel { OneViewModel(get()) }
    viewModel { TwoViewModel(get()) }
    viewModel { ThreeViewModel(get()) }

    viewModel { (id : Int) -> MainViewModel(id) }
}
