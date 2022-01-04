package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.domain.PhotographersDomain

interface PhotographersDataToDomainMapper : Abstract.Mapper {
    fun map(photographers: List<PhotographerParameters>) : PhotographersDomain

    fun map(e: Exception) : PhotographersDomain

}