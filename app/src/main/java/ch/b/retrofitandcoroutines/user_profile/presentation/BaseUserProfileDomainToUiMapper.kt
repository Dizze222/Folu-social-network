package ch.b.retrofitandcoroutines.user_profile.presentation

class BaseUserProfileDomainToUiMapper : UserProfileDomainToUiMapper<UserProfileUi> {
    override fun map(fullName: String, bio: String, image: String): UserProfileUi {
        return UserProfileUi.Base(fullName, bio, image)
    }
}