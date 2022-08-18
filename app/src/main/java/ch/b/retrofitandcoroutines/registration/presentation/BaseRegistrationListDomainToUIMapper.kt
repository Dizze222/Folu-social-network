package ch.b.retrofitandcoroutines.registration.presentation

import ch.b.retrofitandcoroutines.registration.domain.RegistrationDomain
import ch.b.retrofitandcoroutines.registration.domain.RegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomainToUIMapper

class BaseRegistrationListDomainToUIMapper(
    private val mapper: RegistrationDomainToUIMapper<RegistrationUI>
) : RegistrationListDomainToUIMapper(){
    override fun map(error: String): RegistrationListUI {
        return RegistrationListUI.Fail(error)
    }


    override fun map(): RegistrationListUI {
        return Unit as RegistrationListUI //TODO fix this
    }

    override fun map(data: List<RegistrationDomain>): RegistrationListUI {
        return RegistrationListUI.Success(data, mapper)
    }

}