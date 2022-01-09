package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographersCacheMapper : Abstract.Mapper {
    fun map(listPhotographers : List<PhotographerDataBase>) : List<PhotographerParameters>

    class Base(private val mapper: PhotographerCacheMapper) : PhotographersCacheMapper{
        override fun map(listPhotographers: List<PhotographerDataBase>) = listPhotographers.map { photographerDB ->
            photographerDB.map(mapper)
        }

    }
}