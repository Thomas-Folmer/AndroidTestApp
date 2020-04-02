package com.opengroupe.androidtestapp.ui.search

import android.annotation.SuppressLint
import com.opengroupe.androidtestapp.GithubSearchApplication
import com.opengroupe.androidtestapp.R
import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.data.remote.ApiHelper
import com.opengroupe.androidtestapp.utils.rx.SchedulerProvider
import javax.inject.Inject
import com.opengroupe.androidtestapp.data.remote.CallbackWrapper


class SearchRepositoryPresenter<V : SearchRepositoryMvpView> @Inject constructor(val apiHelper: ApiHelper, val scheduler: SchedulerProvider) :  SearchRepositoryMvpPresenter<V> {
    private var mvpView: V? = null

    @SuppressLint("CheckResult")
    override fun onSearchRepositoryClick(searchQueryMap: Map<String,Any>) {

        // display progress bar
        getMvpView()?.showLoading()

        apiHelper.searchRepo(searchQueryMap)?.subscribeOn(scheduler.io())?.observeOn(scheduler.ui())?.subscribeWith(object : CallbackWrapper<SearchRepositoryResponse>() {

                    override fun onSuccess(t: SearchRepositoryResponse) {
                        // dismiss the progress bar
                        getMvpView()?.hideLoading()

                        // send back result to activity
                        if(!t.items.isNullOrEmpty()){
                            getMvpView()?.onFetchedRepositories(t)
                        } else {
                            getMvpView()?.emptyResult()
                        }
                    }

                    override fun onError(any: Any) {
                        // dismiss the progress bar
                        // error handling
                        getMvpView()?.let {view ->
                            view.hideLoading()
                            if (!view.isNetworkConnected){
                                view.onError(GithubSearchApplication.getContext()?.getString(R.string.no_network))
                            } else {
                                view.onError(any.toString())
                            }
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

    fun getMvpView(): V? = mvpView


}