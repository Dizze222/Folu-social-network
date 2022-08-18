package ch.b.retrofitandcoroutines.all_posts

import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.core.Abstract

abstract class BasePhotographerRepositoryTest {

    protected class TestToPhotographerMapper : Abstract.ToPhotographerMapper<PhotographerData> {
        override fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>,
            favourite: Boolean
        ): PhotographerData =
            PhotographerData.Base(id, author, URL, like, theme, comments, authorOfComments,favourite)

    }


}


