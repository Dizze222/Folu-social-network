package ch.b.retrofitandcoroutines.all_posts.presentation.core

import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerUI


class BasePhotographerDomainToUIMapper : PhotographerDomainToUIMapper<PhotographerUI> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
        favourite: Boolean
    ): PhotographerUI =
        PhotographerUI.Base(id, author, URL, like, theme, comments, authorOfComments, favourite)
}