package ch.b.retrofitandcoroutines.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.domain.PhotographerDomain
import io.realm.RealmList

interface PhotographerDataToDomainMapper : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: RealmList<String>,
        authorOfComments: RealmList<String>
    ): PhotographerDomain
}