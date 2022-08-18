package ch.b.retrofitandcoroutines.registration.presentation

import ch.b.retrofitandcoroutines.registration.domain.RegistrationDomainToUIMapper

class BaseRegistrationDomainToUIMapper : RegistrationDomainToUIMapper<RegistrationUI> {
    override fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ): RegistrationUI {
       return RegistrationUI.Base(accessToken, refreshToken, successRegister)
    }

}