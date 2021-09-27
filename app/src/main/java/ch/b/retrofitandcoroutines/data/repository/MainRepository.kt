package ch.b.retrofitandcoroutines.data.repository

import ch.b.retrofitandcoroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}