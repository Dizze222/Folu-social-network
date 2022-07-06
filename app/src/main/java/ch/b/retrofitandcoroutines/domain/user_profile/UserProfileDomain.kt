package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileDomainToUiMapper

interface UserProfileDomain {

    fun <T> map(mapper: UserProfileDomainToUiMapper<T>): T

    class Base(
        private val name: String,
        private val secondName: String,
        private val dio: String,
        private val image: String
    ) : UserProfileDomain{
        override fun <T> map(mapper: UserProfileDomainToUiMapper<T>): T =
            mapper.map(name, secondName, dio, image)

    }



}