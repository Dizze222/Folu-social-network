package ch.b.retrofitandcoroutines.domain.authorization

import ch.b.retrofitandcoroutines.data.authorization.AuthenticationRepository
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomain

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