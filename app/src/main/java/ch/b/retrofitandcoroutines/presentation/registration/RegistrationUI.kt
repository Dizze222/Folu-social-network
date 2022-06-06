package ch.b.retrofitandcoroutines.presentation.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BaseSingleStringMapper

sealed class RegistrationUI : Abstract.Object<Unit, BaseSingleStringMapper.SingleStringMapper> {
    override fun map(mapper: BaseSingleStringMapper.SingleStringMapper) = Unit

    object Progress : RegistrationUI()


    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationUI() {
        override fun map(mapper: BaseSingleStringMapper.SingleStringMapper) =
            mapper.map(accessToken, refreshToken, successRegister)
    }

    class Fail(private val message: String) : RegistrationUI() {
        override fun map(mapper: BaseSingleStringMapper.SingleStringMapper) = mapper.map(message)
    }


}