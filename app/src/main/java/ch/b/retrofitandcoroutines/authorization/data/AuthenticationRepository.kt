package ch.b.retrofitandcoroutines.authorization.data


import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.utils.core_network.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.BaseRepositoryAuth
import ch.b.retrofitandcoroutines.utils.core_network.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationCloudDataSource
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    registrationCloudDataSource: RegistrationCloudDataSource,
    cloudMapper: AuthorizationListCloudMapper,
    exceptionRegisterMapper: ExceptionAuthMapper.Registration,
    exceptionAuthMapper: ExceptionAuthMapper.Authorization,
    tokenToSharedPreferences: TokenToSharedPreferences,
    authenticationCloudDataSource: AuthenticationCloudDataSource
) :
    BaseRepositoryAuth(
        cloudMapper,
        exceptionAuthMapper,
        exceptionRegisterMapper,
        tokenToSharedPreferences,
        registrationCloudDataSource,
        authenticationCloudDataSource
    )