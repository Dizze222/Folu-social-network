package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.domain.core.ErrorType
import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomain
import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider

class BaseRegistrationListDomainToUIMapper(
    private val mapper: RegistrationDomainToUIMapper<RegistrationUI>,
    private val resourceProvider: ResourceProvider
) : RegistrationListDomainToUIMapper(){
    override fun map(errorType: ErrorType): RegistrationListUI {
        return RegistrationListUI.Fail(errorType, resourceProvider)
    }

    override fun map(): RegistrationListUI {
        return Unit as RegistrationListUI //TODO fix this
    }

    override fun map(data: List<RegistrationDomain>): RegistrationListUI {
        return RegistrationListUI.Success(data,mapper)
    }

}