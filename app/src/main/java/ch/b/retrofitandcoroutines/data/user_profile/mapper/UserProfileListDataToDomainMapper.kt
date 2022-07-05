package ch.b.retrofitandcoroutines.data.user_profile.mapper

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.user_profile.UserProfileData
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomain

abstract class UserProfileListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<UserProfileData>,UserProfileListDomain>