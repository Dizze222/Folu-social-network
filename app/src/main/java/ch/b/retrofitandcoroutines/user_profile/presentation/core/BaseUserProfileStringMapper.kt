package ch.b.retrofitandcoroutines.user_profile.presentation.core

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface BaseUserProfileStringMapper : Abstract.Mapper,
    Abstract.Object<Unit, BaseUserProfileStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            name: String,
            bio: String,
            image: String
        )


        fun map(message: String)

        fun map(progress: Boolean)

    }



}