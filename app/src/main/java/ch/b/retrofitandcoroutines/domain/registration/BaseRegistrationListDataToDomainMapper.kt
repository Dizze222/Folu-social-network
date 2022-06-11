package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationDataToDomainMapper
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListDataToDomainMapper

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
        return RegistrationListDomain.Base(data,mapper)
    }
}