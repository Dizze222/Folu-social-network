package ch.b.retrofitandcoroutines.user_profile.presentation

import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileDomain
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomainToUiMapper

class BaseUserProfileListDomainToUiMapper(private val mapper: UserProfileDomainToUiMapper<UserProfileUi>) : UserProfileListDomainToUiMapper() {
    override fun map(error: String): UserProfileListUi =
        UserProfileListUi.Fail(error)

    override fun map(): UserProfileListUi {
        TODO("Not yet implemented")
    }

    override fun map(data: List<UserProfileDomain>): UserProfileListUi =
        UserProfileListUi.Success(data, mapper)


}