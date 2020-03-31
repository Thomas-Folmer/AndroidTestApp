
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable

interface ApiHelper {
    fun searchRepo(searchQuery: String,
                   page: Int,
                   sort: String,
                   order: String,
                   perPage: Int): Observable<SearchRepositoryResponse>?
}