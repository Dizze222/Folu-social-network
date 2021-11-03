package ch.b.retrofitandcoroutines.data.repository

import ch.b.retrofitandcoroutines.data.api.ApiService

class MainDataSource(private val apiService: ApiService) {

  suspend fun getUsers() = apiService.getUsers()

}

