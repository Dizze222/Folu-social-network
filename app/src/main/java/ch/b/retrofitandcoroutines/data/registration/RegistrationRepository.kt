package ch.b.retrofitandcoroutines.data.registration

import ch.b.retrofitandcoroutines.data.core.ExceptionMapper
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListCloudMapper
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource


interface RegistrationRepository {
    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): RegistrationListData

    class Base(
        private val dataSource: RegistrationCloudDataSource,
        private val cloudMapper: RegistrationListCloudMapper,
        private val exceptionMapper: ExceptionMapper
    ) : RegistrationRepository {
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
            val errorMessage = exceptionMapper.mapper(e)
            RegistrationListData.Fail(errorMessage)
        }
    }


}
