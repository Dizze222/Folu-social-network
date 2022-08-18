package ch.b.retrofitandcoroutines.registration.presentation

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.presentation.core.BaseSingleRegistrationStringMapper

sealed class RegistrationUI : Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper> {
    override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) = Unit

    object Progress : RegistrationUI(){
        override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) =
            mapper.map(true)
    }


    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationUI() {
        override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) {
            mapper.map(accessToken, refreshToken, successRegister)
        }

    }

    class Fail(private val message: String) : RegistrationUI() {
        override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) = mapper.map(message)
    }


}