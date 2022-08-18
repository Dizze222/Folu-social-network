package ch.b.retrofitandcoroutines.all_posts.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.cache.CachePhotographer

class BaseToCachePhotographerMapper :
    Abstract.ToPhotographerMapper<CachePhotographer.Base> {
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