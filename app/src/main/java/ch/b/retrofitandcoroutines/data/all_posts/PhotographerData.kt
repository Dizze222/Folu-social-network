package ch.b.retrofitandcoroutines.data.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain


class PhotographerData(
    private val id: Int,
    private val author: String,
    private val URL: String,
    private val like: Long,
    private val theme: String,
    private val comments: List<String>,
    private val authorOfComments: List<String>
) : Abstract.Object<PhotographerDomain, PhotographerDataToDomainMapper> {


    override fun map(mapper: PhotographerDataToDomainMapper): PhotographerDomain =
        mapper.map(id, author, URL, like, theme, comments, authorOfComments)
}

