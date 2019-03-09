package me.khol.koin

import android.app.Application
import me.khol.koin.di.repositoryModule
import me.khol.koin.di.viewModelModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(KoinApplication.create().modules(
            repositoryModule,
            viewModelModule
        ))
    }
}