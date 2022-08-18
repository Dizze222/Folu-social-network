package ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface AuthorizationDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ) : T
}