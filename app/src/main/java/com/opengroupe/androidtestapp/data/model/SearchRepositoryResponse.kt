package com.opengroupe.androidtestapp.data.model

import android.os.Parcelable
import com.opengroupe.androidtestapp.data.remote.BaseResponse
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchRepositoryResponse(

        @Json(name = "total_count")
        val totalCount: Int?,

        @Json(name = "incomplete_results")
        val incompleteResults: Boolean?,

        @Json(name = "items")
        val items: List<RepoItem>
) : BaseResponse(), Parcelable