package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData


class ToPhotographerMapper :
    Abstract.ToPhotographerMapper<PhotographerData> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
        favourite: Boolean
    ): PhotographerData {
        return PhotographerData.Base(
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