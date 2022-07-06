package ch.b.retrofitandcoroutines.presentation.user_profile

import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileDomain
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomainToUiMapper

class BaseUserProfileListDomainToUiMapper(private val mapper: UserProfileDomainToUiMapper<UserProfileUi>) : UserProfileListDomainToUiMapper() {
    override fun map(error: String): UserProfileListUi =
        UserProfileListUi.Fail(error)

    override fun map(): UserProfileListUi {
        TODO("Not yet implemented")
    }

    override fun map(data: List<UserProfileDomain>): UserProfileListUi =
        UserProfileListUi.Success(data,mapper)


}