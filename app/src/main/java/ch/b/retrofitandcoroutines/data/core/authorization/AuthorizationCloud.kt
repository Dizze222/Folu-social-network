package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.Abstract


interface AuthorizationCloud {
    fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T

    data class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : AuthorizationCloud {
        override fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T =
            mapper.map(accessToken, refreshToken, successRegister)
    }

}
