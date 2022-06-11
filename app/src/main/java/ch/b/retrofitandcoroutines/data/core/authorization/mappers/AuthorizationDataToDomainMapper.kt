package ch.b.retrofitandcoroutines.data.core.authorization.mappers

import ch.b.retrofitandcoroutines.core.Abstract

interface AuthorizationDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ) : T
}