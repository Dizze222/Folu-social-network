package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper


class BasePhotographerDataToDomainMapper : PhotographerDataToDomainMapper<PhotographerDomain> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>
    ) =
        PhotographerDomain.Base(id, author, URL, like, theme, comments,authorOfComments)
}