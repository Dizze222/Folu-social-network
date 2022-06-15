package ch.b.retrofitandcoroutines.data.authorization.net.update_token

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud
import retrofit2.Response
import retrofit2.http.*

interface UpdateTokenService {
    @GET("/token/refresh")
    suspend fun updateToken(): AuthorizationCloud.Base
}