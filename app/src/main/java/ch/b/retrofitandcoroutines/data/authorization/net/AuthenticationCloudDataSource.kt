package ch.b.retrofitandcoroutines.data.authorization.net

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud

interface AuthenticationCloudDataSource {
    suspend fun authentication(
        phoneNumber: Long,
        password: String
    ): List<AuthorizationCloud.Base>

    class Base(private val authenticationService: AuthenticationService) :
        AuthenticationCloudDataSource {

        override suspend fun authentication(
            phoneNumber: Long,
            password: String
        ): List<AuthorizationCloud.Base> {
            return authenticationService.authentication(phoneNumber, password).body()!!
        }


    }


}