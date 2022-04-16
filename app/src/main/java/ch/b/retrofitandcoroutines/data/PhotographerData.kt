package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDBMapper
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.PhotographersDomain
import io.realm.Realm
import io.realm.RealmList

class PhotographerData(
    private val id: Int,
    private val author: String,
    private val URL: String,
    private val like: Long,
    private val theme: String,
    private val comments: RealmList<String>,
    private val authorOfComments: RealmList<String>
) : ToPhotographerDB<PhotographerDataBase, PhotographerDataToDBMapper>,
    Abstract.Object<PhotographerDomain, PhotographerDataToDomainMapper> {

    override fun mapTo(mapper: PhotographerDataToDBMapper, realm: Realm): PhotographerDataBase =
        mapper.map(id, author, URL, like, theme, realm,comments,authorOfComments)

    override fun map(mapper: PhotographerDataToDomainMapper): PhotographerDomain =
        mapper.map(id, author, URL, like, theme,comments,authorOfComments)
}

interface ToPhotographerDB<T, M : Abstract.Mapper> {
    fun mapTo(mapper: M, realm: Realm): T
}
