package ch.b.retrofitandcoroutines.data.certain_post.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface CertainPhotographerService {

    @GET("/posts/{id}")
    suspend fun getCertainPost(
        @Path("id") postId: Int
    ) : ResponseBody

}