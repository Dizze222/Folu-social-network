package ch.b.retrofitandcoroutines.data.core.authorization.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.core.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomain

abstract class AuthorizationListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<AuthorizationData>,RegistrationListDomain>