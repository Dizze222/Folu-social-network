package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographersCacheMapper : Abstract.Mapper {
    fun map(listPhotographers : List<PhotographerDB>) : List<PhotographerParameters>

    class Base(private val mapper: PhotographerCacheMapper) : PhotographersCacheMapper{
        override fun map(listPhotographers: List<PhotographerDB>) = listPhotographers.map {photographerDB ->
            photographerDB.map(mapper)
        }

    }
}