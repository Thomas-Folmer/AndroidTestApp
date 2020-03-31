
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val restApi: RestApi) : ApiHelper {
    override fun searchRepo(searchQuery: String,
                            page: Int,
                            sort: String,
                            order: String,
                            perPage: Int): Observable<SearchRepositoryResponse> = restApi.searchRepo(searchQuery,page, sort, order, perPage)
}