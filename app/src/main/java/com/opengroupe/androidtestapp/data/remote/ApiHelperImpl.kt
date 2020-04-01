
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val restApi: RestApi) : ApiHelper {
    override fun searchRepo(searchQueryMap: Map<String,Any>): Observable<SearchRepositoryResponse> = restApi.searchRepo(searchQueryMap)
}