package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.RegistrationData
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.core.ErrorType
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationListUI
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class RegistrationListDomain :
    Abstract.Object<RegistrationListUI, RegistrationListDomainToUIMapper> {
    class Base(
        private val register: List<RegistrationData>,
        private val registerMapper: RegistrationDataToDomainMapper<RegistrationDomain>
    ) : RegistrationListDomain() {
        override fun map(mapper: RegistrationListDomainToUIMapper): RegistrationListUI {
            val registrationDomain = register.map {
                it.map(registerMapper)
            }
            return mapper.map(registrationDomain)
        }
    }

    class Fail(private val e: Exception) : RegistrationListDomain() {
        override fun map(mapper: RegistrationListDomainToUIMapper): RegistrationListUI = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
