package ch.b.retrofitandcoroutines.all_posts.data.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData


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