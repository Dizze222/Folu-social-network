package ch.b.retrofitandcoroutines.data.user_profile.network

import retrofit2.Response
import retrofit2.http.*

interface UserProfileService {
    @FormUrlEncoded
    @POST("profile")
    suspend fun sendPhoto(@Field("image") photoProfile: String)

    @GET("profile")
    suspend fun getProfileInfo() : Response<List<UserProfileCloud.Base>>

}