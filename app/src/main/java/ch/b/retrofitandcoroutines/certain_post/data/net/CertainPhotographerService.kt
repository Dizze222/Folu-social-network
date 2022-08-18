package ch.b.retrofitandcoroutines.certain_post.data.net

import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CertainPhotographerService {

    @GET("/posts/{id}")
    suspend fun getCertainPost(
        @Path("id") postId: Int
    ) :  Response<List<PhotographerCloud.Base>>

}