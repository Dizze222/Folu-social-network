package ch.b.retrofitandcoroutines.presentation.user_profile

class BaseUserProfileDomainToUiMapper : UserProfileDomainToUiMapper<UserProfileUi> {
    override fun map(name: String, secondName: String, bio: String, image: String): UserProfileUi {
        return UserProfileUi.Base(name, secondName, bio, image)
    }
}