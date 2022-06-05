package ch.b.retrofitandcoroutines.data.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationUI

interface RegistrationData : Abstract.Mapper {
    fun <T> map(mapper: RegistrationDataToDomainMapper<T>): T
    fun map(mapper: RegistrationUI.StringMapper) = Unit
    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationData {
        override fun <T> map(mapper: RegistrationDataToDomainMapper<T>): T {
            return mapper.map(accessToken, refreshToken, successRegister)
        }

    }

    interface StringMapper : Abstract.Mapper {
        fun map(
            accessToken: String,
            refreshToken: String,
            successRegister: Boolean
        )

        fun map(message: String)
    }
}
