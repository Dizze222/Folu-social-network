package ch.b.retrofitandcoroutines.data.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface PhotographersDataSource{
    suspend fun getPhotographers() : List<PhotographerCloud>
    class Base(private val service: PhotographerService) : PhotographersDataSource{
        val gson = Gson()
        private val type = object : TypeToken<List<PhotographerCloud>>() {}.type
        override suspend fun getPhotographers(): List<PhotographerCloud> = gson.fromJson(service.getPhotographers().string(),type)

    }
}