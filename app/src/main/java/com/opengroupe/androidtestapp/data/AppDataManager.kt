package com.opengroupe.androidtestapp.data

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.data.remote.ApiHelper
import io.reactivex.Observable
import javax.inject.Inject

open class AppDataManager @Inject constructor(
    private val apiHelper: ApiHelper) : DataManager {


    override fun searchRepo(searchQueryMap: Map<String,Any>):
            Observable<SearchRepositoryResponse>? = apiHelper.searchRepo(searchQueryMap)


}