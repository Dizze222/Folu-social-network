package ch.b.retrofitandcoroutines.authorization.domain

import ch.b.retrofitandcoroutines.authorization.data.AuthenticationRepository
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomain

interface AuthenticationInteractor {

    suspend fun authentication(phoneNumber: Long, password: String): RegistrationListDomain

    class Base(
        private val repository: AuthenticationRepository,
        private val mapper: AuthorizationListDataToDomainMapper
    ) : AuthenticationInteractor {
        override suspend fun authentication(
            phoneNumber: Long,
            password: String
        ): RegistrationListDomain {
            return repository.auth(phoneNumber,"","",password,"auth").map(mapper)
        }
    }
}