package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationDataToDomainMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListDataToDomainMapper

class BaseRegistrationListDataToDomainMapper(
    private val mapper: AuthorizationDataToDomainMapper<RegistrationDomain>
) : AuthorizationListDataToDomainMapper() {

    override fun map(error: String): RegistrationListDomain {
        return RegistrationListDomain.Fail(error)
    }

    override fun map(): RegistrationListDomain {
        return Unit as RegistrationListDomain //TODO fix this
    }

    override fun map(data: List<AuthorizationData>): RegistrationListDomain {
        return RegistrationListDomain.Base(data, mapper)
    }
}