package ch.b.retrofitandcoroutines.registration.domain

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface RegistrationDomainToUIMapper<T> : Abstract.Mapper{

    fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ) : T

}