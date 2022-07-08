package ch.b.retrofitandcoroutines.presentation.user_profile

class BaseUserProfileDomainToUiMapper : UserProfileDomainToUiMapper<UserProfileUi> {
    override fun map(fullName: String, bio: String, image: String): UserProfileUi {
        return UserProfileUi.Base(fullName, bio, image)
    }
}