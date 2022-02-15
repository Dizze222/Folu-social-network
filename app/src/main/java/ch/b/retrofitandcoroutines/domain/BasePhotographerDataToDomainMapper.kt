package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper

class BasePhotographerDataToDomainMapper : PhotographerDataToDomainMapper {
    override fun map(id: Int, author: String, URL: String, like: Long, theme: String) =
        PhotographerDomain(id, author, URL, like, theme)
}