package me.khol.koin

import android.app.Application
import me.khol.koin.di.repositoryModule
import me.khol.koin.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            repositoryModule,
            viewModelModule
        ))
    }
}