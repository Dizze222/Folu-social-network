package ch.b.retrofitandcoroutines.domain.registration

import ch.b.retrofitandcoroutines.core.Abstract

interface RegistrationDomainToUIMapper<T> : Abstract.Mapper{

    fun map(
        accessToken: String,
        refreshToken: String,
        successRegister: Boolean
    ) : T

}