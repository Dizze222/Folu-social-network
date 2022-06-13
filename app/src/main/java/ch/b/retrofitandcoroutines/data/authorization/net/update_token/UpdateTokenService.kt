package ch.b.retrofitandcoroutines.data.authorization.net.update_token

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud
import retrofit2.Response
import retrofit2.http.GET

interface UpdateTokenService {
    @GET("/token/refresh")
    fun updateToken() : Response<List<AuthorizationCloud.Base>>
}