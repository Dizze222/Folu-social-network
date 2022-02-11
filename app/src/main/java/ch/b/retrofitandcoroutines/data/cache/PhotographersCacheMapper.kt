package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper

interface PhotographersCacheMapper : Abstract.Mapper {
    fun map(listPhotographers : List<Abstract.Object<PhotographerData, ToPhotographerMapper>>) : List<PhotographerData>

    class Base(private val mapper: ToPhotographerMapper) : PhotographersCacheMapper{
        override fun map(listPhotographers: List<Abstract.Object<PhotographerData, ToPhotographerMapper>>) = listPhotographers.map { photographerDB ->
            photographerDB.map(mapper)
        }
    }
}