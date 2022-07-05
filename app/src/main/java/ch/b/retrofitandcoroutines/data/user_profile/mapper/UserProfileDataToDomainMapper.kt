package ch.b.retrofitandcoroutines.data.user_profile.mapper

import ch.b.retrofitandcoroutines.core.Abstract

interface UserProfileDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        name: String,
        secondName: String,
        dio: String,
        image: String
    ): T

}