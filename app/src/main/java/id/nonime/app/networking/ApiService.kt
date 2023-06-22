package id.nonime.app.networking

import id.nonime.app.models.GenreListModel
import id.nonime.app.models.HomeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("genres")
    fun getGenres(): Call<GenreListModel>

    @GET("home")
    fun getHome(): Call<HomeModel>
}