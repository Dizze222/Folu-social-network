package ch.b.retrofitandcoroutines.data.api

import ch.b.retrofitandcoroutines.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<User>
}