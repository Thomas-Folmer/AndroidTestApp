
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RestApi {

    @GET("/search/repositories")
    fun searchRepo(@QueryMap queryMap : Map<String, @JvmSuppressWildcards  Any>): Observable<SearchRepositoryResponse>

}
