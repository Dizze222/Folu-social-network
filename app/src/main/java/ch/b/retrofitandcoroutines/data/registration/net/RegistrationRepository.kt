package ch.b.retrofitandcoroutines.data.registration.net

import ch.b.retrofitandcoroutines.data.registration.RegistrationListData
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListCloudMapper

interface RegistrationRepository {
    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): RegistrationListData

    class Base(
        private val dataSource: RegistrationCloudDataSource,
        private val cloudMapper: RegistrationListCloudMapper
    ) :
        RegistrationRepository {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListData = try {
            val listOfCloud = dataSource.register(phoneNumber, name, secondName, password)
            val registerList = cloudMapper.map(listOfCloud)
            RegistrationListData.Success(registerList)
        } catch (e: Exception) {
            RegistrationListData.Fail(e)
        }
    }
}