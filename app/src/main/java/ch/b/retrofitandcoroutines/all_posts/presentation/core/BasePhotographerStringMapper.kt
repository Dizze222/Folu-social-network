package ch.b.retrofitandcoroutines.all_posts.presentation.core

import ch.b.retrofitandcoroutines.utils.core.Abstract

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