package ch.b.retrofitandcoroutines.data.registration.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.RegistrationData
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomain

abstract class RegistrationListDataToDomainMapper : Abstract.Mapper.DataToDomain.Base<List<RegistrationData>,RegistrationListDomain>()