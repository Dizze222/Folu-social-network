package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.mappers.PhotographersDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographersDomain
import java.lang.Exception

sealed class PhotographersData : Abstract.Object<PhotographersDomain, PhotographersDataToDomainMapper> {
    data class Success(private val photographers: List<PhotographerData>) : PhotographersData(){
        override fun map(mapper: PhotographersDataToDomainMapper) = mapper.map(photographers)
    }
    data class Fail(private val e: Exception) : PhotographersData() {
        override fun map(mapper: PhotographersDataToDomainMapper) = mapper.map(e)
    }

    object EmptyData : PhotographersData() {
        override fun map(mapper: PhotographersDataToDomainMapper) = mapper.map()
    }

}