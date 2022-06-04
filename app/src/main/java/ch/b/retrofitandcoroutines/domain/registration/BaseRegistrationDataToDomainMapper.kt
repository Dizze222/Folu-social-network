package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationDataToDomainMapper

class BaseRegistrationDataToDomainMapper : RegistrationDataToDomainMapper<RegistrationDomain> {
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationDomain {
        return RegistrationDomain.Base(accessToken, refreshToken, successRegister)
    }

}