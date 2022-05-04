package ch.b.retrofitandcoroutines.data.all_posts.net

import com.google.gson.reflect.TypeToken
import retrofit2.Response

interface PhotographersCloudDataSource {
    suspend fun getPhotographers(): List<PhotographerCloud.Base>

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
        val type = object : TypeToken<List<PhotographerCloud>>(){}.type
        override suspend fun getPhotographers(): List<PhotographerCloud.Base> =
            service.getPhotographers().body()!!

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