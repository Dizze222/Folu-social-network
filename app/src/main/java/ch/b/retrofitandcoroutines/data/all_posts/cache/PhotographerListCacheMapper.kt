package ch.b.retrofitandcoroutines.data.all_posts.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToPhotographerMapper


interface PhotographerListCacheMapper : Abstract.Mapper {
    fun map(listPhotographers : List<Abstract.Object<PhotographerData, ToPhotographerMapper>>) : List<PhotographerData>

    class Base(private val mapper: ToPhotographerMapper) : PhotographerListCacheMapper {
        override fun map(listPhotographers: List<Abstract.Object<PhotographerData, ToPhotographerMapper>>) = listPhotographers.map { photographerDB ->
            photographerDB.map(mapper)
        }

    }
}