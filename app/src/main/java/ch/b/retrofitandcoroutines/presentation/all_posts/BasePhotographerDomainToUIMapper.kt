package ch.b.retrofitandcoroutines.presentation.all_posts

import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper


class BasePhotographerDomainToUIMapper : PhotographerDomainToUIMapper {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>
    ): PhotographerUI =
        PhotographerUI.Base(id, author, URL, like, theme, comments, authorOfComments)
}