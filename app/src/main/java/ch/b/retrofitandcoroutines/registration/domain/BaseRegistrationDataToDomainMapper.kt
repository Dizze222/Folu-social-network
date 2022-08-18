package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationDataToDomainMapper

class BaseRegistrationDataToDomainMapper : AuthorizationDataToDomainMapper<RegistrationDomain> {
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationDomain {
        return RegistrationDomain.Base(accessToken, refreshToken, successRegister)
    }

}