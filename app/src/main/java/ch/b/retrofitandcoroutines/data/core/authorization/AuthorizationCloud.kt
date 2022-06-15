package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper


interface AuthorizationCloud {
    fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T
    fun map(mapperr: BaseSingleRegistrationStringMapper.SingleStringMapper)
    suspend fun map(mapperr: BaseSingleRegistrationStringMapper.AuthenticatorStringMapper)

    data class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : AuthorizationCloud {
        override fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T =
            mapper.map(accessToken, refreshToken, successRegister)

        override fun map(mapperr: BaseSingleRegistrationStringMapper.SingleStringMapper) =
            mapperr.map(accessToken, refreshToken, successRegister)

        override suspend fun map(mapperr: BaseSingleRegistrationStringMapper.AuthenticatorStringMapper) {
            mapperr.map(accessToken, refreshToken)
        }
    }
}
