package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData

abstract class BasePhotographerRepositoryTest {

    protected class TestToBookMapper : Abstract.ToPhotographerMapper<PhotographerData> {
        override fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: List<String>,
            authorOfComments: List<String>
        ): PhotographerData =
            PhotographerData.Base(id, author, URL, like, theme, comments, authorOfComments)

    }


}


