package id.nonime.app.view_model

import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.nonime.app.models.DetailAnimeModel
import id.nonime.app.networking.ApiConfig
import id.nonime.app.utils.Helpers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAnimeViewModel(private val id: String) : ViewModel() {
    private val _data = MutableLiveData<DetailAnimeModel>()
    val data: LiveData<DetailAnimeModel> get() = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError
    var errorMessage: String = ""

    init {
        _isLoading.value = true
        _isError.value = false
        fetchDetail(id)
    }

    private fun fetchDetail(id: String) {

        val client = ApiConfig.getApiService().getDetail(id)

        client.enqueue(object : Callback<DetailAnimeModel> {
            override fun onResponse(
                call: Call<DetailAnimeModel>,
                response: Response<DetailAnimeModel>
            ) {
                _isLoading.value = false

                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    _isError.value = true
                    errorMessage = Helpers.onError()
                    return
                }
                _data.postValue(responseBody)
            }

            override fun onFailure(call: Call<DetailAnimeModel>, t: Throwable) {
                _isLoading.value = false
                _isError.value = true
                t.message?.let { msg -> errorMessage = Helpers.onError(msg) }
            }
        })
    }
}