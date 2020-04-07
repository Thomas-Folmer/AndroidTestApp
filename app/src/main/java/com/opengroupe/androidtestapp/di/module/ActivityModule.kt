package com.opengroupe.androidtestapp.di.module

import com.opengroupe.androidtestapp.ui.search.SearchRepositoryContract
import com.opengroupe.androidtestapp.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideSearchRepositoryPresenter(searchPresenter: SearchPresenter<SearchRepositoryContract.View>): SearchRepositoryContract.Presenter<SearchRepositoryContract.View> = searchPresenter

}