package com.opengroupe.androidtestapp.ui.search

import com.opengroupe.androidtestapp.data.DataManager
import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.ui.base.BasePresenter
import com.opengroupe.androidtestapp.utils.rx.SchedulerProvider
import javax.inject.Inject
import com.opengroupe.androidtestapp.data.remote.CallbackWrapper


class SearchRepositoryPresenter<V : SearchRepositoryMvpView> @Inject constructor(dm: DataManager, val scheduler: SchedulerProvider) : BasePresenter<V>(dm), SearchRepositoryMvpPresenter<V> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun onSearchRepositoryClick(searchQueryMap: Map<String,Any>) {

        // display progress bar
        getMvpView()?.showLoading()

        getDataManager()?.searchRepo(searchQueryMap)?.subscribeOn(scheduler.io())?.observeOn(scheduler.ui())?.subscribeWith(object : CallbackWrapper<SearchRepositoryResponse>() {

                    override fun onSuccess(searchRepositoryResponse: SearchRepositoryResponse) {
                        // dismiss the progress bar
                        getMvpView()?.hideLoading()

                        // send back result to activity
                        if(!searchRepositoryResponse.items.isNullOrEmpty()){
                            getMvpView()?.onFetchedRepositories(searchRepositoryResponse)
                        } else {
                            getMvpView()?.emtyResult()
                        }
                    }

                    override fun onError(error: Any) {
                        // dismiss the progress bar
                        getMvpView()?.hideLoading()
                        if (getMvpView()?.isNetworkConnected!!){
                            handleApiError(error)
                        } else {
                            handleApiError(error)
                        }
                        // error handling
                    }
                })
    }
}