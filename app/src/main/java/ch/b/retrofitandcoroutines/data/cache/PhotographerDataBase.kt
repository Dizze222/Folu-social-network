package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PhotographerDataBase : RealmObject(),Abstract.Object<PhotographerData, ToPhotographerMapper>{
    @PrimaryKey
    var id: Int = -1
    var author: String = ""
    var URL: String = ""
    var like: Long = -1
    var theme: String = ""
    override fun map(mapper: ToPhotographerMapper) = PhotographerData(id,author,URL,like,theme)
}
