package com.opengroupe.androidtestapp.ui.search


interface SearchRepositoryMvpPresenter<V : SearchRepositoryMvpView> {
    fun onSearchRepositoryClick(searchQueryMap : Map<String,Any>)
    fun onAttach(mvpView: V)
    fun onDetach()
    fun handleApiError(error: Any)
}