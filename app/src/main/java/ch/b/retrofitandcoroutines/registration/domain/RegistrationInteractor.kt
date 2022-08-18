package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.data.RegistrationRepository

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