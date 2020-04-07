package com.opengroupe.androidtestapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class SearchRepositoryResponse(

        @Json(name = "total_count")
        val totalCount: Int?,

        @Json(name = "incomplete_results")
        val incompleteResults: Boolean?,

        @Json(name = "items")
        val items: List<RepoItem>
)