package ch.b.retrofitandcoroutines.user_profile.data.mapper

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileData
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomain

abstract class UserProfileListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<UserProfileData>, UserProfileListDomain>