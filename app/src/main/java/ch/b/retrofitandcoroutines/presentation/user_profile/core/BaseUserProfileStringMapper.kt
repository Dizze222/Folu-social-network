package ch.b.retrofitandcoroutines.presentation.user_profile.core

import ch.b.retrofitandcoroutines.core.Abstract

interface BaseUserProfileStringMapper : Abstract.Mapper,
    Abstract.Object<Unit, BaseUserProfileStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            name: String,
            secondName: String,
            bio: String,
            image: String
        )


        fun map(message: String)

        fun map(progress: Boolean)

    }



}