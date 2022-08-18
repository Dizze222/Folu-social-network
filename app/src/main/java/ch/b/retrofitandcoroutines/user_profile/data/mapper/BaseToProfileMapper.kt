package ch.b.retrofitandcoroutines.user_profile.data.mapper

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileData

class BaseToProfileMapper : Abstract.ToProfileMapper<UserProfileData> {
    override fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): UserProfileData {
        return UserProfileData.Base(name, secondName, bio, image)
    }


}