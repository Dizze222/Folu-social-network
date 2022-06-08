package ch.b.retrofitandcoroutines.data.registration

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationDataToDomainMapper

interface RegistrationData : Abstract.Mapper,Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper> {
    fun <T> map(mapper: RegistrationDataToDomainMapper<T>): T

    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationData {
        override fun <T> map(mapper: RegistrationDataToDomainMapper<T>): T {
            return mapper.map(accessToken, refreshToken, successRegister)
        }

        override fun map(mapper: BaseSingleRegistrationStringMapper.SingleStringMapper) {
            mapper.map(accessToken, refreshToken, successRegister)
        }


    }

}
