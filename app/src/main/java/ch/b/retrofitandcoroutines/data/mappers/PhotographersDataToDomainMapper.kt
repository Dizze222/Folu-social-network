package ch.b.retrofitandcoroutines.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.domain.PhotographersDomain

interface PhotographersDataToDomainMapper : Abstract.Mapper {
    fun map(photographers: List<PhotographerData>) : PhotographersDomain

    fun map(e: Exception) : PhotographersDomain

    fun map() : PhotographersDomain
}