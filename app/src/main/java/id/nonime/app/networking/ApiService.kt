package id.nonime.app.networking

import id.nonime.app.models.GenreListModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("genres")
    fun getGenres(): Call<GenreListModel>
}