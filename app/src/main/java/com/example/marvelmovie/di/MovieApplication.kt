package com.example.marvelmovie.di

import android.app.Application
import com.example.marvelmovie.di.MovieModule.instance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication: Application() {

        override fun onCreate() {
            super.onCreate()

            startKoin {
                androidContext(this@MovieApplication)
                modules(instance)

            }
        }
}