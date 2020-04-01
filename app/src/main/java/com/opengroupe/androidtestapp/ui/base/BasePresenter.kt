package com.opengroupe.androidtestapp.ui.base

import com.opengroupe.androidtestapp.data.remote.ApiHelper
import com.opengroupe.androidtestapp.ui.search.SearchRepositoryMvpPresenter
import com.opengroupe.androidtestapp.ui.search.SearchRepositoryMvpView


open class BasePresenter<V : SearchRepositoryMvpView> constructor(private val dm: ApiHelper) : SearchRepositoryMvpPresenter<V> {

    private var mvpView: V? = null

    fun getDataManager(): ApiHelper? = dm

    override fun onDetach() {
        mvpView = null
    }

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    fun getMvpView(): V? = mvpView

    override fun onSearchRepositoryClick(searchQueryMap: Map<String, Any>) {
        TODO("Not yet implemented")
    }

}


