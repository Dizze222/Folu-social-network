package ch.b.retrofitandcoroutines.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PhotographerService {
    @GET("list")
    suspend fun getPhotographers() : ResponseBody
}