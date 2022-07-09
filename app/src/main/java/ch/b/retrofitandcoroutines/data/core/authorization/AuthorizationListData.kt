package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomain

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