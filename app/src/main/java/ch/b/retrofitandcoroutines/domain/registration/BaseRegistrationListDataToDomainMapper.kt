package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.data.registration.RegistrationData
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListDataToDomainMapper

class BaseRegistrationListDataToDomainMapper(
    private val mapper: RegistrationDataToDomainMapper<RegistrationDomain>
) : RegistrationListDataToDomainMapper() {

    override fun map(error: String): RegistrationListDomain {
        return RegistrationListDomain.Fail(error)
    }

    override fun map(): RegistrationListDomain {
        return Unit as RegistrationListDomain //TODO fix this
    }

    override fun map(data: List<RegistrationData>): RegistrationListDomain {
        return RegistrationListDomain.Base(data,mapper)
    }
}