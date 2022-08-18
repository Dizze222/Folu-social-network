package ch.b.retrofitandcoroutines.registration.presentation

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.registration.domain.RegistrationDomain
import ch.b.retrofitandcoroutines.registration.domain.RegistrationDomainToUIMapper


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