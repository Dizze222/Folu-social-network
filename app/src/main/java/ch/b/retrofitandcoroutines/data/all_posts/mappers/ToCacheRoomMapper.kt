package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer

interface ToCacheRoomMapper : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
        favourite: Boolean
    ): CachePhotographer.Base


    class Base : ToCacheRoomMapper {
        override fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>,
            favourite: Boolean
        ) = CachePhotographer.Base(
            id,
            author,
            URL,
            like,
            theme,
            comments,
            authorOfComments,
            favourite
        )
    }
}