package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper


interface AuthorizationCloud {
    fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T
    fun map(mapperr: BaseSingleRegistrationStringMapper.SingleStringMapper)

    data class Base(
        val accessToken: String,
        val refreshToken: String,
        val successRegister: Boolean
    ) : AuthorizationCloud {
        override fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T =
            mapper.map(accessToken, refreshToken, successRegister)

        override fun map(mapperr: BaseSingleRegistrationStringMapper.SingleStringMapper) =
            mapperr.map(accessToken, refreshToken, successRegister)
    }

}
