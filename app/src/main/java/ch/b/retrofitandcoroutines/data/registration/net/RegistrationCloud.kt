package ch.b.retrofitandcoroutines.data.registration.net

import ch.b.retrofitandcoroutines.core.Abstract


interface RegistrationCloud {
    fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T

    data class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationCloud {
        override fun <T> map(mapper: Abstract.ToRegisterMapper<T>): T =
            mapper.map(accessToken, refreshToken, successRegister)
    }

}
