package ch.b.retrofitandcoroutines.data.core.authorization

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationDataToDomainMapper

interface AuthorizationData : Abstract.Mapper,Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper> {
    fun <T> map(mapper: AuthorizationDataToDomainMapper<T>): T
    fun accessToken() : String
    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : AuthorizationData {
        override fun <T> map(mapper: AuthorizationDataToDomainMapper<T>): T {
            return mapper.map(accessToken, refreshToken, successRegister)
        }

        override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) {
            mapper.map(accessToken, refreshToken, successRegister)
        }

        override fun accessToken(): String {
            return accessToken
        }
    }

}
