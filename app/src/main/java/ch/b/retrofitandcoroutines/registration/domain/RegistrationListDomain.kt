package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationListUI

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
