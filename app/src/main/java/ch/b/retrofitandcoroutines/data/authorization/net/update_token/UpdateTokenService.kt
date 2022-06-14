package ch.b.retrofitandcoroutines.data.authorization.net.update_token

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

interface UpdateTokenService {
    @GET("/token/refresh")
    suspend fun updateToken(
        @Header("identity") refreshToken: String
    ): AuthorizationCloud.Base
}