package com.opengroupe.androidtestapp.ui.base


interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: Any)

}