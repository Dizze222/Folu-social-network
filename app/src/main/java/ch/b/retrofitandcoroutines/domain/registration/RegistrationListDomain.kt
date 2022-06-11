package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationListUI

sealed class RegistrationListDomain :
    Abstract.Object<RegistrationListUI, RegistrationListDomainToUIMapper> {
    class Base(
        private val register: List<AuthorizationData>,
        private val registerMapper: AuthorizationDataToDomainMapper<RegistrationDomain>
    ) : RegistrationListDomain() {
        override fun map(mapper: RegistrationListDomainToUIMapper): RegistrationListUI {
            val registrationDomain = register.map { it.map(registerMapper) }
            return mapper.map(registrationDomain)
        }
    }

    class Fail(private val message: String) : RegistrationListDomain() {
        override fun map(mapper: RegistrationListDomainToUIMapper): RegistrationListUI =
            mapper.map(message)
    }
}
