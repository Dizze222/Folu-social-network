package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationDataToDomainMapper

class BaseRegistrationDataToDomainMapper : AuthorizationDataToDomainMapper<RegistrationDomain> {
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationDomain {
        return RegistrationDomain.Base(accessToken, refreshToken, successRegister)
    }

}