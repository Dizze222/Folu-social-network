package ch.b.retrofitandcoroutines.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.data.api.ApiService
import ch.b.retrofitandcoroutines.data.api.RetrofitBuilder
import ch.b.retrofitandcoroutines.data.model.User
import ch.b.retrofitandcoroutines.data.repository.MainRepository
import ch.b.retrofitandcoroutines.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (exception: Exception){
            emit(Resource.error(data = null,message = exception.message ?: "Error Occurred"))
        }
    }

}