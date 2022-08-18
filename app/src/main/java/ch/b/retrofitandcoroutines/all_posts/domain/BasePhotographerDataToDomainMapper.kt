package ch.b.retrofitandcoroutines.all_posts.domain

import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper


class BasePhotographerDataToDomainMapper : PhotographerDataToDomainMapper<PhotographerDomain> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
         favourite: Boolean
    ) =
        PhotographerDomain.Base(id, author, URL, like, theme, comments, authorOfComments, favourite)
}