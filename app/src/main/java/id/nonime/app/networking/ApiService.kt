package id.nonime.app.networking

import id.nonime.app.models.DetailAnimeModel
import id.nonime.app.models.GenreListModel
import id.nonime.app.models.HomeModel
import id.nonime.app.models.SearchResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("genres")
    fun getGenres(): Call<GenreListModel>

    @GET("home")
    fun getHome(): Call<HomeModel>

    @GET("anime/{id}")
    fun getDetail(@Path("id") id: String): Call<DetailAnimeModel>

    @GET("search/{keyword}")
    fun searchAnime(@Path("keyword") keyword: String): Call<SearchResultModel>
}