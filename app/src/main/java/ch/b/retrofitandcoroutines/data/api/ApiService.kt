package ch.b.retrofitandcoroutines.data.api

import android.util.Log
import ch.b.retrofitandcoroutines.data.model.UserDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getUsers() : List<UserDTO>
}
