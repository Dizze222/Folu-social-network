package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.domain.PhotographerDomainToUIMapper
import io.realm.RealmList

class BasePhotographerDomainToUIMapper : PhotographerDomainToUIMapper{
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: RealmList<String>,
        authorOfComments: RealmList<String>
    ): PhotographerUI = PhotographerUI.Base(id, author, URL, like, theme,comments,authorOfComments)
}