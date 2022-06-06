package ch.b.retrofitandcoroutines.core

interface BaseSingleStringMapper : Abstract.Mapper, Abstract.Object<Unit,BaseSingleStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            accessToken: String,
            refreshToken: String,
            successRegister: Boolean
        )

        fun map(message: String)
    }

}