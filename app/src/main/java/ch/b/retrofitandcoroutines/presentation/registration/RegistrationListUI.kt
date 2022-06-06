package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.domain.core.ErrorType
import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomain
import ch.b.retrofitandcoroutines.domain.registration.RegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider


sealed class RegistrationListUI : Abstract.Object<Unit, RegistrationCommunication> {

    class Success(
        private val registrationList: List<RegistrationDomain>,
        private val registrationMapper: RegistrationDomainToUIMapper<RegistrationUI>
    ) : RegistrationListUI() {
        override fun map(mapper: RegistrationCommunication) {
            val registrationUI = registrationList.map {
                it.map(registrationMapper)
            }
            mapper.map(registrationUI)
        }
    }

    class Fail(
        private val error: String
    ) : RegistrationListUI() {
        override fun map(mapper: RegistrationCommunication) {
            mapper.map(listOf(RegistrationUI.Fail(error)))
        }

    }


}