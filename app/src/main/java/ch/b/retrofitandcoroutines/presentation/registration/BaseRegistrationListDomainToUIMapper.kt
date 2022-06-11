package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomain
import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider

class BaseRegistrationListDomainToUIMapper(
    private val mapper: RegistrationDomainToUIMapper<RegistrationUI>,
    private val resourceProvider: ResourceProvider
) : RegistrationListDomainToUIMapper(){
    override fun map(error: String): RegistrationListUI {
        return RegistrationListUI.Fail(error)
    }


    override fun map(): RegistrationListUI {
        return Unit as RegistrationListUI //TODO fix this
    }

    override fun map(data: List<RegistrationDomain>): RegistrationListUI {
        return RegistrationListUI.Success(data,mapper)
    }

}