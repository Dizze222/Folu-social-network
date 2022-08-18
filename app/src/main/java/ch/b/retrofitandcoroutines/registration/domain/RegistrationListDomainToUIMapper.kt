package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationListUI

abstract class RegistrationListDomainToUIMapper : Abstract.Mapper.DomainToUi<List<RegistrationDomain>, RegistrationListUI>