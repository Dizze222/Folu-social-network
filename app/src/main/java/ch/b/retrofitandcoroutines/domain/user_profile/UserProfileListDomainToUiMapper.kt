package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileListUi

abstract class UserProfileListDomainToUiMapper :
    Abstract.Mapper.DomainToUi<List<UserProfileDomain>, UserProfileListUi>