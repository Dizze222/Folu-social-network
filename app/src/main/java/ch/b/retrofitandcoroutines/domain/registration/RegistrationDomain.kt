package ch.b.retrofitandcoroutines.domain.registration

interface RegistrationDomain {

    fun <T> map(mapper: RegistrationDomainToUIMapper<T>): T

    class Base(
        private val accessToken: String,
        private val refreshToken: String,
        private val successRegister: Boolean
    ) : RegistrationDomain {
        override fun <T> map(mapper: RegistrationDomainToUIMapper<T>): T {
            return mapper.map(accessToken, refreshToken, successRegister)
        }
    }
}