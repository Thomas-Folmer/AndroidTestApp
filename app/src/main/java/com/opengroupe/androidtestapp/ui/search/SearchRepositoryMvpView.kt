package com.opengroupe.androidtestapp.ui.search

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse

interface SearchRepositoryMvpView {
    fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse)

    val isNetworkConnected: Boolean

    fun showLoading()
    fun hideLoading()
    fun onError(message: String?)
    fun emptyResult()

}