package ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomain

abstract class AuthorizationListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<AuthorizationData>, RegistrationListDomain>