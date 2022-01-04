package ch.b.retrofitandcoroutines.data.net

import retrofit2.http.GET

interface PhotographerService {
    @GET("list")
    suspend fun getPhotographers() : List<PhotographerCloud>
}