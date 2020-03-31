package com.opengroupe.androidtestapp.ui.search

import com.opengroupe.androidtestapp.ui.base.MvpPresenter


interface SearchRepositoryMvpPresenter<V : SearchRepositoryMvpView> : MvpPresenter<V> {

    fun onSearchRepositoryClick(query: String ,page: Int,
                                sort: String,
                                order: String,
                                perPage: Int)
}