package ch.b.retrofitandcoroutines.ui.main.viewmodel

import androidx.lifecycle.*
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.data.repository.MainRepository
import ch.b.retrofitandcoroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val _newResponse = MutableLiveData<Resource<List<UserDTO>>>()

    val newResponse: LiveData<Resource<List<UserDTO>>> = _newResponse

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _newResponse.postValue(Resource.Loading(data = null))
            try {
                _newResponse.postValue(Resource.Success(data = mainRepository.getUsers()))
            }catch (e: Exception){
                _newResponse.postValue(Resource.Error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }
    }
}