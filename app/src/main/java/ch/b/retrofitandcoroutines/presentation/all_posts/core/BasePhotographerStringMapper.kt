package ch.b.retrofitandcoroutines.presentation.all_posts.core

import ch.b.retrofitandcoroutines.core.Abstract

interface BasePhotographerStringMapper : Abstract.Mapper,
    Abstract.Object<Unit, BasePhotographerStringMapper.SingleStringMapper> {

    interface SingleStringMapper : Abstract.Mapper {
        fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>
        )


        fun map(message: String)

        fun map(progress: Boolean)

    }

    interface IdMapper : Abstract.Mapper {
        fun map(id:Int)
    }


}