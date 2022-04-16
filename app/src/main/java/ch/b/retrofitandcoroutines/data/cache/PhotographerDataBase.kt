package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PhotographerDataBase : RealmObject(),Abstract.Object<PhotographerData, ToPhotographerMapper> {
    @PrimaryKey
    var id: Int = -1
    var author: String = ""
    var URL: String = ""
    var like: Long = -1
    var theme: String = ""
    var comments: RealmList<String> = RealmList()
    var authorOfComments: RealmList<String> = RealmList()
    override fun map(mapper: ToPhotographerMapper) = mapper.map(id, author, URL, like, theme, comments,authorOfComments)
}