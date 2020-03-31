
package com.opengroupe.androidtestapp.data.remote

import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/search/repositories")
    fun searchRepo(@Query("q") query: String,
                   @Query("page")  page : Int,
                   @Query("sort") sort : String,
                   @Query("order") order : String,
                   @Query("per_page")  perPage : Int ): Observable<SearchRepositoryResponse>

}