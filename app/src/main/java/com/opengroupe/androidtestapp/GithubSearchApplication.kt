package com.opengroupe.androidtestapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.opengroupe.androidtestapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GithubSearchApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = androidDispatchingInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
        githubSearchApplication = this

    }
    companion object {
        private var githubSearchApplication: GithubSearchApplication? = null
        @JvmStatic
        fun getContext(): Context? {
            return githubSearchApplication?.getApplicationContext()
        }
    }



}