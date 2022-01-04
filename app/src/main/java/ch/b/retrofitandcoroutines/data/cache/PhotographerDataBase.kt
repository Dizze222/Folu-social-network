package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class PhotographerDataBase : RealmObject(),Abstract.Mapable<PhotographerParameters,PhotographerCacheMapper>{
    @PrimaryKey
    var id: Int = -1
    var author: String = ""
    override fun map(mapper: PhotographerCacheMapper) = PhotographerParameters(id,author)
}