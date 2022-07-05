package ch.b.retrofitandcoroutines.data.user_profile

import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileDataToDomainMapper

interface UserProfileData {
    fun <T> map(mapper: UserProfileDataToDomainMapper<T>): T

    class Base(
        private val name: String,
        private val secondName: String,
        private val dio: String,
        private val image: String
    ) : UserProfileData{
        override fun <T> map(mapper: UserProfileDataToDomainMapper<T>): T =
            mapper.map(name, secondName, dio, image)
    }

}