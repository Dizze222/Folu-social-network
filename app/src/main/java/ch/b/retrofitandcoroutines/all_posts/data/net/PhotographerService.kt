package ch.b.retrofitandcoroutines.all_posts.data.net


import retrofit2.Response
import retrofit2.http.*

interface PhotographerService {
    @GET("posts")
    suspend fun getPhotographers() : Response<List<PhotographerCloud.Base>>

    @FormUrlEncoded
    @POST("posts")
    suspend fun like(
        @Field("author") author: String,
        @Field("idPhotographer") idPhotographer: Int,
        @Field("like")like: Int,
        @Field("theme") theme: String,
        @Field("url") url: String
    ) : Response<PhotographerCloud>

}