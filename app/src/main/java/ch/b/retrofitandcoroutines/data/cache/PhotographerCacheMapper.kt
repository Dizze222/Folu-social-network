package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographerCacheMapper : Abstract.Mapper {
    fun map(photographerDB: PhotographerDB) : PhotographerParameters

    class Base : PhotographerCacheMapper{
        override fun map(photographerDB: PhotographerDB)
        = PhotographerParameters(photographerDB.id,photographerDB.author)

    }
}