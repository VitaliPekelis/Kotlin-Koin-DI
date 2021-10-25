package co.vitali.koindi

import android.app.Application
import co.vitali.koindi.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App :Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(provideDependences())
        }
    }

    open fun provideDependences() = appComponent
}