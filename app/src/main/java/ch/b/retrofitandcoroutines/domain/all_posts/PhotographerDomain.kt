package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import io.realm.RealmList


class PhotographerDomain(
    private val id: Int,
    private val author: String,
    private val URL: String,
    private val like: Long,
    private val theme: String,
    private val comments: RealmList<String>,
    private val authorOfComments: RealmList<String>
) : Abstract.Object<PhotographerUI, PhotographerDomainToUIMapper> {
    override fun map(mapper: PhotographerDomainToUIMapper): PhotographerUI =
        mapper.map(id, author, URL, like, theme,comments,authorOfComments)

}