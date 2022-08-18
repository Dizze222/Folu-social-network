package ch.b.retrofitandcoroutines.user_profile.presentation

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface UserProfileDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        fullName: String,
        bio: String,
        image: String
    ): T
}