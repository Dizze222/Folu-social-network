package ch.b.retrofitandcoroutines.data.registration.mappers

import ch.b.retrofitandcoroutines.core.Abstract

interface RegistrationDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ) : T
}