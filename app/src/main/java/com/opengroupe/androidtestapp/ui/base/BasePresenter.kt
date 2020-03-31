package com.opengroupe.androidtestapp.ui.base

import com.opengroupe.androidtestapp.data.DataManager


open class BasePresenter<V : MvpView> constructor(private val dm: DataManager) : MvpPresenter<V> {

    private var mvpView: V? = null

    fun getDataManager(): DataManager? = dm

    override fun onDetach() {
        mvpView = null
    }

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    fun getMvpView(): V? = mvpView

    override fun handleApiError(error: Any) {
        mvpView?.onError(error.toString())
    }

}


