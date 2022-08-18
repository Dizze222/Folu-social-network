package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileListUi

abstract class UserProfileListDomainToUiMapper :
    Abstract.Mapper.DomainToUi<List<UserProfileDomain>, UserProfileListUi>