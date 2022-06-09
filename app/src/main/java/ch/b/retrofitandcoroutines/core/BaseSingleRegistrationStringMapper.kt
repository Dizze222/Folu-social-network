package ch.b.retrofitandcoroutines.core

interface BaseSingleRegistrationStringMapper : Abstract.Mapper, Abstract.Object<Unit,BaseSingleRegistrationStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            accessToken: String,
            refreshToken: String,
            successRegister: Boolean
        )

        fun map(message: String)

        fun map(progress: Boolean)
    }

}