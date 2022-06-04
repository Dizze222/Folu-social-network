package ch.b.retrofitandcoroutines.data.registration.net

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface RegistrationService {
    @POST("/register")
    suspend fun registration(
        @Field("phoneNumber") phoneNumber: Long,
        @Field("name") name: String,
        @Field("secondName") secondName: String,
        @Field("password") password: String
    ): Response<List<RegistrationCloud.Base>>
}