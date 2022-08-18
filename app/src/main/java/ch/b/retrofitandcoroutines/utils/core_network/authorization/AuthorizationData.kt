package ch.b.retrofitandcoroutines.utils.core_network.authorization

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.presentation.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationDataToDomainMapper

interface AuthorizationData : Abstract.Mapper,
    Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper> {
    fun <T> map(mapper: AuthorizationDataToDomainMapper<T>): T
    fun accessToken(): String
    data class Base(
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
