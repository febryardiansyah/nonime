package id.nonime.app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.nonime.app.models.GenreListModel
import id.nonime.app.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreListViewModel : ViewModel() {
    private val _genreData = MutableLiveData<GenreListModel>()
    val genreData: LiveData<GenreListModel> get() = _genreData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError
    var errorMessage: String = ""

    fun fetchGenresData() {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getGenres()

        client.enqueue(object : Callback<GenreListModel> {
            override fun onResponse(
                call: Call<GenreListModel>,
                response: Response<GenreListModel>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    _isError.value = true
                    onError("Data processing error")
                    errorMessage = "Data processing error"
                    return
                }
                _genreData.postValue(responseBody)
            }

            override fun onFailure(call: Call<GenreListModel>, t: Throwable) {
                t.message?.let { onError(it) }
                t.printStackTrace()
            }

        })
    }

    private fun onError(message: String) {
        val msg =
            if (message.isNullOrBlank() or message.isNullOrEmpty()) "Unkown error" else message
        errorMessage = StringBuilder("Error: ").append("$msg some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }
}