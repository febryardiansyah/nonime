package id.nonime.app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.nonime.app.models.HomeModel
import id.nonime.app.networking.ApiConfig
import id.nonime.app.utils.Helpers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _data = MutableLiveData<HomeModel>()
    val data: LiveData<HomeModel> get() = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError
    var errorMessage: String = ""

    fun fetchHome() {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getHome()

        client.enqueue(object : Callback<HomeModel> {
            override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                _isLoading.value = false
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null){
                    _isError.value = true
                    Helpers.onError()
                    errorMessage = "Data processing error"
                    return
                }
                _data.postValue(responseBody)
            }

            override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                t.message?.let { Helpers.onError(it, t) }
            }

        })
    }
}