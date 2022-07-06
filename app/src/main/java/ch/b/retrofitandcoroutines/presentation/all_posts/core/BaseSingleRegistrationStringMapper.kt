package ch.b.retrofitandcoroutines.presentation.all_posts.core

import ch.b.retrofitandcoroutines.core.Abstract

interface BaseSingleRegistrationStringMapper : Abstract.Mapper,
    Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            accessToken: String,
            refreshToken: String,
            successRegister: Boolean
        )

        fun map(message: String)

        fun map(progress: Boolean)
    }

    interface AuthenticatorStringMapper : Abstract.Mapper {
        suspend fun map(
            accessToken: String,
            refreshToken: String
        )
    }


}