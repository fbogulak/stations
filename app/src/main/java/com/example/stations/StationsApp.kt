package com.example.stations

import android.app.Application
import com.example.stations.database.StationsDatabase
import com.example.stations.distance.DistanceViewModel
import com.example.stations.repository.BaseRepository
import com.example.stations.repository.StationsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class StationsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            single { StationsDatabase.getInstance(this@StationsApp) }
            single { StationsRepository(get()) as BaseRepository }
            viewModel { DistanceViewModel(get()) }
        }
        startKoin {
            androidContext(this@StationsApp)
            modules(appModule)
        }
    }
}