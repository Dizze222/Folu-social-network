package ch.b.retrofitandcoroutines.presentation.user_profile

import ch.b.retrofitandcoroutines.core.Abstract

interface UserProfileDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): T
}