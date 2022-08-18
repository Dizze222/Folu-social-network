package ch.b.retrofitandcoroutines.user_profile.data.mapper

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface UserProfileDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): T

}