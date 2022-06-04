package ch.b.retrofitandcoroutines.data.registration.net

interface RegistrationCloudDataSource {
    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): List<RegistrationCloud.Base>

    class Base(private val service: RegistrationService) : RegistrationCloudDataSource {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): List<RegistrationCloud.Base> {
            return service.registration(phoneNumber, name, secondName, password).body()!!
        }
    }
}