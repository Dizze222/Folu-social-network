package ch.b.retrofitandcoroutines.data.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

interface PhotographersCloudDataSource {
    suspend fun getPhotographers(): List<PhotographerCloud>

    suspend fun makePost(
        author: String,
        idPhotographer: Int,
        like: Int,
        theme: String,
        url: String
    ): Response<PhotographerCloud>

    class Base(
        private val service: PhotographerService,
    ) : PhotographersCloudDataSource {
        val gson = Gson()
        val type = object : TypeToken<List<PhotographerCloud>>(){}.type
        override suspend fun getPhotographers(): List<PhotographerCloud> =
            gson.fromJson(service.getPhotographers().string(), type)

        override suspend fun makePost(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ): Response<PhotographerCloud> =
            service.makePost(author, idPhotographer, like, theme, url)

    }
}