package ch.b.retrofitandcoroutines.data.authorization.net

import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloud

interface AuthenticationCloudDataSource {

    suspend fun authentication(
        phoneNumber: Long,
        password: String
    ): List<RegistrationCloud.Base>


    class Base(private val authenticationService: AuthenticationService) :
        AuthenticationCloudDataSource {
        override suspend fun authentication(
            phoneNumber: Long,
            password: String
        ): List<RegistrationCloud.Base> {
            return authenticationService.authentication(phoneNumber, password).body()!!
        }
    }


}