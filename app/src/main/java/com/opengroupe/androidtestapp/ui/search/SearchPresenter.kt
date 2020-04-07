package com.opengroupe.androidtestapp.ui.search

import android.annotation.SuppressLint
import com.opengroupe.androidtestapp.GithubSearchApplication
import com.opengroupe.androidtestapp.R
import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.data.remote.ApiHelper
import com.opengroupe.androidtestapp.utils.rx.SchedulerProvider
import javax.inject.Inject
import com.opengroupe.androidtestapp.data.remote.CallbackWrapper


class SearchPresenter<V : SearchRepositoryContract.View> @Inject constructor(val apiHelper: ApiHelper, val scheduler: SchedulerProvider) :
    SearchRepositoryContract.Presenter<V> {
    private var mvpView: V? = null

    @SuppressLint("CheckResult")
    override fun onSearchRepositoryClick(searchQueryMap: Map<String,Any>) {

        // display progress bar
        mvpView?.showLoading()

        apiHelper.searchRepo(searchQueryMap).subscribeOn(scheduler.io())?.observeOn(scheduler.ui())?.subscribe({
            mvpView?.hideLoading()
            // send back result to activity
            if(!it.items.isNullOrEmpty()){
                mvpView?.onFetchedRepositories(it)
            } else {
                mvpView?.emptyResult()
            }
        },{
            mvpView?.let {view ->
                view.hideLoading()
                if (!view.isNetworkConnected){
                    view.onError(GithubSearchApplication.getContext()?.getString(R.string.no_network))
                } else {
                    view.onError(it.toString())
                }
            }
        })
    }



    override fun onDetach() {
        mvpView = null
    }

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }



}