package ch.b.retrofitandcoroutines.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData

interface ToPhotographerMapper : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String
    ): PhotographerData

    class Base : ToPhotographerMapper {
        override fun map(id: Int, author: String, URL: String, like: Long, theme: String) =
            PhotographerData(id, author, URL, like, theme)
    }
}