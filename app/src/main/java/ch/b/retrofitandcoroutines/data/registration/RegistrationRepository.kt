package ch.b.retrofitandcoroutines.data.registration

import ch.b.retrofitandcoroutines.core.dataOfRegister
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.data.core.TokenToSharedPreferences
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
        private val exceptionMapper: ExceptionAuthMapper,
        private val tokenToSharedPreferences: TokenToSharedPreferences
    ) : RegistrationRepository {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListData = try {
            val listOfCloud = dataSource.register(phoneNumber, name, secondName, password)
            val registerList: List<RegistrationData> = cloudMapper.map(listOfCloud)
            tokenToSharedPreferences.saveRefreshToken(registerList.dataOfRegister()[1])
            tokenToSharedPreferences.saveAccessToken(registerList.dataOfRegister()[0])
            RegistrationListData.Success(registerList)
        } catch (e: Exception) {
            val errorMessage = exceptionMapper.mapper(e)
            RegistrationListData.Fail(errorMessage)
        }
    }

}
