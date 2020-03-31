
package com.opengroupe.androidtestapp.di.component

import com.opengroupe.androidtestapp.GithubSearchApplication
import com.opengroupe.androidtestapp.di.builder.ActivityBuilder
import com.opengroupe.androidtestapp.di.module.ActivityModule
import com.opengroupe.androidtestapp.di.module.HelpersModule
import com.opengroupe.androidtestapp.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = arrayOf(AndroidSupportInjectionModule::class, HelpersModule::class, NetworkModule::class,
        ActivityBuilder::class, ActivityModule::class))
@Singleton
interface AppComponent {

    fun inject(app: GithubSearchApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GithubSearchApplication): Builder

        fun build(): AppComponent
    }
}