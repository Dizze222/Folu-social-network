package ch.b.retrofitandcoroutines.presentation.user_profile

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.user_profile.core.BaseUserProfileStringMapper

sealed class UserProfileUi : Abstract.Object<Unit, BaseUserProfileStringMapper.SingleStringMapper> {
    class Base(
        private val name: String,
        private val secondName: String,
        private val bio: String,
        private val image: String
    ) : UserProfileUi() {
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(name, secondName, bio, image)
        }
    }

    class Fail(private val message: String) : UserProfileUi(){
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(message)
        }

    }
}