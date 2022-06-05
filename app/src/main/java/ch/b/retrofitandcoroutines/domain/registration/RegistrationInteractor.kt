package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListDataToDomainMapper
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
        private val mapper: RegistrationListDataToDomainMapper
    ) : RegistrationInteractor {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): RegistrationListDomain {
            return repository.register(phoneNumber, name, secondName, password).map(mapper)
        }

    }

}