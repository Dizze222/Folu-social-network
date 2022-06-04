package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomainToUIMapper

class BaseRegistrationDomainToUIMapper : RegistrationDomainToUIMapper<RegistrationUI>{
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationUI {
       return RegistrationUI.Base(accessToken,refreshToken,successRegister)
    }

}