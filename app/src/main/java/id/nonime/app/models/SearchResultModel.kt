package id.nonime.app.models

import com.google.gson.annotations.SerializedName

data class SearchResultModel (
    @field:SerializedName("search_results")
    val results: List<AnimeItemModel>? = null,
)