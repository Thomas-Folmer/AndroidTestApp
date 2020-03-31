package com.opengroupe.androidtestapp.data

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.data.remote.ApiHelper
import io.reactivex.Observable
import javax.inject.Inject

open class AppDataManager @Inject constructor(
    private val apiHelper: ApiHelper) : DataManager {


    override fun searchRepo(
        searchQuery: String,
        page: Int,
        sort: String,
        order: String,
        perPage: Int
    ): Observable<SearchRepositoryResponse>? = apiHelper.searchRepo(searchQuery,page,sort,order,perPage)


}