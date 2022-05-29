package com.example.samplegithubrepository

import android.app.Application
import com.example.samplegithubrepository.di.AppModule
import com.example.samplegithubrepository.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            //androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(listOf(AppModule, NetworkModule))
        }

    }
}