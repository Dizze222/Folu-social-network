package ch.b.retrofitandcoroutines.utils.core_network.authorization

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomain

sealed class AuthorizationListData :
    Abstract.Object<RegistrationListDomain, AuthorizationListDataToDomainMapper> {

    data class Success(private val registrations: List<AuthorizationData>) :
        AuthorizationListData() {
        override fun map(mapper: AuthorizationListDataToDomainMapper): RegistrationListDomain {
            return mapper.map(registrations)
        }
    }

    data class Fail(private val message: String) : AuthorizationListData() {
        override fun map(mapper: AuthorizationListDataToDomainMapper): RegistrationListDomain {
            return mapper.map(message)
        }
    }
}