package id.nonime.app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.nonime.app.models.SearchResultModel
import id.nonime.app.networking.ApiConfig
import id.nonime.app.utils.Helpers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultViewModel(keyword: String) : ViewModel() {
    private val _data = MutableLiveData<SearchResultModel>()
    val data: LiveData<SearchResultModel> get() = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    var errorMessage: String = ""

    init {
        _isLoading.value = true
        fetchList(keyword)
    }

    private fun fetchList(keyword: String) {
        val client = ApiConfig.getApiService().searchAnime(keyword)

        client.enqueue(object : Callback<SearchResultModel> {
            override fun onResponse(
                call: Call<SearchResultModel>,
                response: Response<SearchResultModel>
            ) {
                _isLoading.value = false

                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null){
                    errorMessage = Helpers.onError()
                    return
                }
                _data.postValue(responseBody)
            }

            override fun onFailure(call: Call<SearchResultModel>, t: Throwable) {
                _isLoading.value = false
                t.message?.let { errorMessage = Helpers.onError(it) }
            }

        })
    }
}