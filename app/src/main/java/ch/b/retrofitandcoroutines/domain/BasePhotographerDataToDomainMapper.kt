package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import io.realm.RealmList

class BasePhotographerDataToDomainMapper : PhotographerDataToDomainMapper {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: RealmList<String>,
        authorOfComments: RealmList<String>
    ) =
        PhotographerDomain(id, author, URL, like, theme, comments,authorOfComments)
}