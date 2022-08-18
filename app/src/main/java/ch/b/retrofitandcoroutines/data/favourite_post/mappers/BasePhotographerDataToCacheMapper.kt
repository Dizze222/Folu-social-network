package ch.b.retrofitandcoroutines.data.favourite_post.mappers

import ch.b.retrofitandcoroutines.all_posts.data.cache.CachePhotographer
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper

class BasePhotographerDataToCacheMapper : PhotographerDataToDomainMapper<CachePhotographer.Base> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
        favourite: Boolean
    ): CachePhotographer.Base {
        return CachePhotographer.Base(
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