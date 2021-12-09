package ch.b.retrofitandcoroutines.data.repository

import ch.b.retrofitandcoroutines.data.api.ApiService
import ch.b.retrofitandcoroutines.data.model.UserDTO

class MainDataSource(private val apiService: ApiService) {

  suspend fun getUsers()= apiService.getUsers()

}

