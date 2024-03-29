package ch.b.retrofitandcoroutines.registration.data.net

import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationCloud
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegistrationService {
    @FormUrlEncoded
    @POST("/register")
    suspend fun registration(
        @Field("phoneNumber") phoneNumber: Long,
        @Field("name") name: String,
        @Field("secondName") secondName: String,
        @Field("password") password: String
    ): Response<List<AuthorizationCloud.Base>>
}