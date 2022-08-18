package ch.b.retrofitandcoroutines.authorization.data.net.authorization

import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationCloud
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthenticationService {
    @FormUrlEncoded
    @POST("/authentication")
    suspend fun authentication(
        @Field("phoneNumber") phoneNumber: Long,
        @Field("password") password: String
    ): Response<List<AuthorizationCloud.Base>>
}