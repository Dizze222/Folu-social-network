package ch.b.retrofitandcoroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ch.b.retrofitandcoroutines.data.repository.MainRepository
import ch.b.retrofitandcoroutines.utils.Resource
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun getUsers() = liveData{
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (exception: Exception){
            emit(Resource.error(data = null,message = exception.message ?: "Error Occurred"))
        }
    }

}