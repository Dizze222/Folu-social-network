package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.registration.RegistrationRepository

interface RegistrationInteractor {
    suspend fun register(
        phoneNumber: Long,
        name: String,
        secondName: String,
        password: String
    ): RegistrationListDomain

    class Base(
        private val repository: RegistrationRepository,
        private val mapper: AuthorizationListDataToDomainMapper
    ) : RegistrationInteractor {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListDomain {
            return repository.auth(phoneNumber, name, secondName, password,"register").map(mapper)
        }

    }

}