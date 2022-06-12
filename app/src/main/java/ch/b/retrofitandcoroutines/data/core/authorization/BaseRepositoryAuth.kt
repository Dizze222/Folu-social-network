package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.dataOfAuth
import ch.b.retrofitandcoroutines.data.authorization.net.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource
import javax.inject.Inject

open class BaseRepositoryAuth @Inject constructor(
    private val cloudMapper: AuthorizationListCloudMapper,
    private val exceptionMapper: ExceptionAuthMapper,
    private val tokenToSharedPreferences: TokenToSharedPreferences,
    private val registrationCloudDataSource: RegistrationCloudDataSource,
    private val authenticationCloudDataSource: AuthenticationCloudDataSource
) {


    suspend fun auth(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String,
        state: String
    ): AuthorizationListData = try {
        val listOfCloud: List<AuthorizationCloud>?
        if (state == "register"){
            listOfCloud = registrationCloudDataSource.register(phoneNumber, name, secondName, password)
        }else{
            listOfCloud = authenticationCloudDataSource.authentication(phoneNumber, password)
        }
        val registerList = cloudMapper.map(listOfCloud)
        tokenToSharedPreferences.saveAccessToken(registerList.dataOfAuth()[0])
        tokenToSharedPreferences.saveRefreshToken(registerList.dataOfAuth()[1])
        AuthorizationListData.Success(registerList)
    } catch (e: Exception) {
        val errorMessage = exceptionMapper.mapper(e)
        AuthorizationListData.Fail(errorMessage)
    }
}