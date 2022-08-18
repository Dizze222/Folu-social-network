package ch.b.retrofitandcoroutines.user_profile.data

import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileDataToDomainMapper

interface UserProfileData {
    fun <T> map(mapper: UserProfileDataToDomainMapper<T>): T

    /*data*/class Base(
        private val name: String,
        private val secondName: String,
        private val bio: String,
        private val image: String
    ) : UserProfileData {
        override fun <T> map(mapper: UserProfileDataToDomainMapper<T>): T =
            mapper.map(name, secondName, bio, image)

    }

}