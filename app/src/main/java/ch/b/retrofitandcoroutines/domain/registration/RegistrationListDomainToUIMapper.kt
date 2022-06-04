package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationListUI

abstract class RegistrationListDomainToUIMapper : Abstract.Mapper.DomainToUi<List<RegistrationDomain>,RegistrationListUI>