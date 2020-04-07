
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable

interface ApiHelper {
    fun searchRepo(searchQueryMap: Map<String,Any>): Observable<SearchRepositoryResponse>
}