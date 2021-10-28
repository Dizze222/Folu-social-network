package ch.b.retrofitandcoroutines.ui.main.viewmodel

import androidx.lifecycle.*
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.data.repository.MainRepository
import ch.b.retrofitandcoroutines.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
   private var _newResponse = MutableLiveData<Resource<List<UserDTO>>>()

    val newResponse :LiveData<Resource<List<UserDTO>>>
        get() = _newResponse

    fun getUsers(){
        viewModelScope.launch {
            _newResponse.postValue(Resource.success(data = mainRepository.getUsers()))
        }
    }

}