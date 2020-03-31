
package com.opengroupe.androidtestapp.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.opengroupe.androidtestapp.GithubSearchApplication
import com.opengroupe.androidtestapp.data.AppDataManager
import com.opengroupe.androidtestapp.data.DataManager
import com.opengroupe.androidtestapp.data.remote.ApiHelper
import com.opengroupe.androidtestapp.data.remote.ApiHelperImpl
import com.opengroupe.androidtestapp.utils.rx.AppSchedulerProvider
import com.opengroupe.androidtestapp.utils.rx.SchedulerProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class HelpersModule {


    @Provides
    fun provideContext(app: GithubSearchApplication): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @Singleton
    fun provideSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider = appSchedulerProvider

}