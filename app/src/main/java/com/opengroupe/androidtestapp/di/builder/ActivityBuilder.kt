
package com.opengroupe.androidtestapp.di.builder

import com.opengroupe.androidtestapp.ui.search.GithubSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSearchRepositoryActivity(): GithubSearchActivity



}