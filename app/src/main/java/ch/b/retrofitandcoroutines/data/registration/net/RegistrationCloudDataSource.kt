package ch.b.retrofitandcoroutines.data.registration.net

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationCloud

interface RegistrationCloudDataSource {

    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): List<AuthorizationCloud.Base>

    class Base(private val service: RegistrationService) : RegistrationCloudDataSource {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): List<AuthorizationCloud.Base> {
            return service.registration(phoneNumber, name, secondName, password).body()!!
        }

    }

}
