package ch.b.retrofitandcoroutines.all_posts.presentation.core

import ch.b.retrofitandcoroutines.utils.core.Abstract

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