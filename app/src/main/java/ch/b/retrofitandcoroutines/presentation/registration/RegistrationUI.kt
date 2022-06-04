package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.core.Abstract

sealed class RegistrationUI : Abstract.Object<Unit, RegistrationUI.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress : RegistrationUI()


    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationUI() {
        override fun map(mapper: StringMapper) =
            mapper.map(accessToken, refreshToken, successRegister)
    }

    class Fail(private val message: String) : RegistrationUI() {
        override fun map(mapper: StringMapper) = mapper.map(message)
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