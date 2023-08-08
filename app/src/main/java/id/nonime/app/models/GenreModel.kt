package id.nonime.app.models

import com.google.gson.annotations.SerializedName

data class GenreListModel(
    @field:SerializedName("genreList")
    val genreList: List<GenreModel?>? = null
)

data class GenreModel(
    @field:SerializedName("title")
    val genreName: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("img")
    val imageLink: String? = null
)