package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileDomainToUiMapper

interface UserProfileDomain {

    fun <T> map(mapper: UserProfileDomainToUiMapper<T>): T

    class Base(
        private val name: String,
        private val secondName: String,
        private val dio: String,
        private val image: String
    ) : UserProfileDomain {
        override fun <T> map(mapper: UserProfileDomainToUiMapper<T>): T =
            mapper.map(name + secondName, dio, image)
    }
}