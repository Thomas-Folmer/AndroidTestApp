package com.opengroupe.androidtestapp.ui.search

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse

interface SearchRepositoryContract{
    interface View {
        fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse)

        val isNetworkConnected: Boolean

        fun showLoading()
        fun hideLoading()
        fun onError(message: String?)
        fun emptyResult()

    }
    interface Presenter<V : View> {
        fun onSearchRepositoryClick(searchQueryMap : Map<String,Any>)
        fun onAttach(mvpView: V)
        fun onDetach()
    }
}
