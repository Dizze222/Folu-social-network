package ch.b.retrofitandcoroutines.authorization.data.net.update_token

import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData
import retrofit2.http.*

interface UpdateTokenService {
    @GET("/token/refresh")
    suspend fun updateToken(): AuthorizationData.Base
}