package ch.b.retrofitandcoroutines.data.all_posts.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData



interface PhotographerListCacheMapper : Abstract.Mapper {
    fun map(listPhotographers : List<CachePhotographer>) : List<PhotographerData>

    class Base(private val mapper: Abstract.ToCachePhotographerMapper<PhotographerData>) : PhotographerListCacheMapper {
        override fun map(listPhotographers : List<CachePhotographer>) = listPhotographers.map { photographerDB ->
            photographerDB.map(mapper)
        }

    }
}