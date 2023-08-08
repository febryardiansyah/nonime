package id.nonime.app.models

import com.google.gson.annotations.SerializedName

data class HomeModel(
    @field:SerializedName("home")
    val data: HomeDataModel? = null
)

data class HomeDataModel(

    @field:SerializedName("on_going")
    val onGoing: List<AnimeItemModel?>? = null,

    @field:SerializedName("complete")
    val complete: List<AnimeItemModel?>? = null
)

data class AnimeItemModel(

    @field:SerializedName("thumb")
    val thumb: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("episode")
    val episode: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("score")
    val score: Double? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("genre_list")
    val genreList:List<GenreModel>? = null
)