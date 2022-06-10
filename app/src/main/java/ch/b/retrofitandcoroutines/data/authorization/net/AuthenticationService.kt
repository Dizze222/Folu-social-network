package ch.b.retrofitandcoroutines.data.authorization.net

import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloud
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
    ): Response<List<RegistrationCloud.Base>>
}