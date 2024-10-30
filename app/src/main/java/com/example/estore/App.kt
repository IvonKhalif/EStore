package com.example.estore

import android.app.Application
import com.example.estore.utils.modules.NetworkModule
import com.example.estore.utils.modules.RepositoryModule
import com.example.estore.utils.modules.UseCaseModule
import com.example.estore.utils.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    ViewModelModule,
                    UseCaseModule,
                    NetworkModule,
                    RepositoryModule
                )
            )
        }
    }
}