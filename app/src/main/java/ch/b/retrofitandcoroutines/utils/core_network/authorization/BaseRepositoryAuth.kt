package ch.b.retrofitandcoroutines.utils.core_network.authorization

import ch.b.retrofitandcoroutines.utils.core.dataOfAuth
import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.utils.core_network.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationCloudDataSource
import javax.inject.Inject

open class BaseRepositoryAuth @Inject constructor(
    private val cloudMapper: AuthorizationListCloudMapper,
    private val exceptionAuthMapper: ExceptionAuthMapper,
    private val exceptionRegisterMapper: ExceptionAuthMapper,
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
        val listOfCloud: List<AuthorizationCloud> = if (state == "register") {
            registrationCloudDataSource.register(phoneNumber, name, secondName, password)
        } else {
            authenticationCloudDataSource.authentication(phoneNumber, password)
        }
        val registerList = cloudMapper.map(listOfCloud)
        tokenToSharedPreferences.saveAccessToken(registerList.dataOfAuth()[0])
        tokenToSharedPreferences.saveRefreshToken(registerList.dataOfAuth()[1])
        AuthorizationListData.Success(registerList)
    } catch (e: Exception) {
        if (state == "register") {
            val errorMessage = exceptionRegisterMapper.map(e)
            AuthorizationListData.Fail(errorMessage)
        } else {
            val errorMessage = exceptionAuthMapper.map(e)
            AuthorizationListData.Fail(errorMessage)
        }
    }
}