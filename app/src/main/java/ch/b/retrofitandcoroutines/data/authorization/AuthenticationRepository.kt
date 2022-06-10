package ch.b.retrofitandcoroutines.data.authorization

import ch.b.retrofitandcoroutines.core.dataOfRegister
import ch.b.retrofitandcoroutines.data.authorization.net.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.data.core.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.registration.RegistrationListData
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListCloudMapper
import java.lang.Exception

interface AuthenticationRepository {

    suspend fun authorization(
        phoneNumber: Long,
        password: String
    ): RegistrationListData


    class Base(
        private val dataSource: AuthenticationCloudDataSource,
        private val cloudMapper: RegistrationListCloudMapper,
        private val exceptionMapper: ExceptionAuthMapper,
        private val tokenToSharedPreferences: TokenToSharedPreferences
    ) : AuthenticationRepository {
        override suspend fun authorization(
            phoneNumber: Long,
            password: String
        ): RegistrationListData = try {
            val listOfCloud = dataSource.authentication(phoneNumber,password)
            val authList = cloudMapper.map(listOfCloud)
            tokenToSharedPreferences.saveAccessToken(authList.dataOfRegister()[0])
            tokenToSharedPreferences.saveRefreshToken(authList.dataOfRegister()[1])
            RegistrationListData.Success(authList)
        } catch (e: Exception) {
            val message = exceptionMapper.mapper(e)
            RegistrationListData.Fail(message)
        }
    }

}