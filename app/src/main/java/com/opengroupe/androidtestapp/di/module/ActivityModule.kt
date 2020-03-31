package com.opengroupe.androidtestapp.di.module

import com.opengroupe.androidtestapp.ui.search.SearchRepositoryMvpPresenter
import com.opengroupe.androidtestapp.ui.search.SearchRepositoryMvpView
import com.opengroupe.androidtestapp.ui.search.SearchRepositoryPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideSearchRepositoryPresenter(presenter: SearchRepositoryPresenter<SearchRepositoryMvpView>): SearchRepositoryMvpPresenter<SearchRepositoryMvpView> = presenter

}