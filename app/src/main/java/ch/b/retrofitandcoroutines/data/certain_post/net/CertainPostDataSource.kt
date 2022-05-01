package ch.b.retrofitandcoroutines.data.certain_post.net

import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface CertainPostDataSource {

    suspend fun getCertainPost(postId: Int) : List<PhotographerCloud>

    class Base(private val service: CertainPhotographerService) : CertainPostDataSource{
        val gson = Gson()
        val type = object : TypeToken<List<PhotographerCloud>>(){}.type
        override suspend fun getCertainPost(postId: Int): List<PhotographerCloud> =
            gson.fromJson(service.getCertainPost(postId).string(),type)
    }
}