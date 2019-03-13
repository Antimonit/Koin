package me.khol.koin.di

import me.khol.koin.screens.onboarding.OnboardingViewModel
import me.khol.koin.screens.onboarding.step.one.OneViewModel
import me.khol.koin.screens.onboarding.step.three.ThreeViewModel
import me.khol.koin.screens.onboarding.step.two.TwoViewModel
import me.khol.koin.repository.OnboardingRepository
import me.khol.koin.repository.OnboardingRepositoryImpl
import me.khol.koin.repository.UserRepository
import me.khol.koin.repository.UserRepositoryImpl
import me.khol.koin.screens.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val repositoryModule = module {

    scope<OnboardingRepository>(Scopes.ONBOARDING) { OnboardingRepositoryImpl() }

    scope<UserRepository>(Scopes.SIGNED_IN) { UserRepositoryImpl() }
}

val viewModelModule = module {

    viewModel { OnboardingViewModel(get()) }

    viewModel { OneViewModel(get()) }
    viewModel { TwoViewModel(get()) }
    viewModel { ThreeViewModel(get(), get()) }

    viewModel { (id : Int) -> MainViewModel(id) }
}
