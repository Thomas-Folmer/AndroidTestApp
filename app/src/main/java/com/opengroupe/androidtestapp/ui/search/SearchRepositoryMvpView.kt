package com.opengroupe.androidtestapp.ui.search

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.ui.base.MvpView

interface SearchRepositoryMvpView : MvpView {
    fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse)
}