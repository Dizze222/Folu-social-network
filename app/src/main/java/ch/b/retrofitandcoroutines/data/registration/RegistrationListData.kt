package ch.b.retrofitandcoroutines.data.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomain
import java.lang.Exception

sealed class RegistrationListData :
    Abstract.Object<RegistrationListDomain, RegistrationListDataToDomainMapper> {

    class Success(private val registrations: List<RegistrationData>) : RegistrationListData() {
        override fun map(mapper: RegistrationListDataToDomainMapper): RegistrationListDomain {
            return mapper.map(registrations)
        }
    }

    class Fail(private val exception: Exception) : RegistrationListData(){
        override fun map(mapper: RegistrationListDataToDomainMapper): RegistrationListDomain {
            return mapper.map(exception)
        }
    }
}