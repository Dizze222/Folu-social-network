package ch.b.retrofitandcoroutines.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.domain.PhotographerListDomain

interface PhotographerListDataToDomainMapper : Abstract.Mapper {
    fun map(photographers: List<PhotographerData>) : PhotographerListDomain

    fun map(e: Exception) : PhotographerListDomain

    fun map() : PhotographerListDomain
}