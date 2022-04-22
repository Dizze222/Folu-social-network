package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.PhotographerListDomain
import java.lang.Exception

sealed class PhotographerListData : Abstract.Object<PhotographerListDomain, PhotographerListDataToDomainMapper> {
    data class Success(private val photographers: List<PhotographerData>) : PhotographerListData(){
        override fun map(mapper: PhotographerListDataToDomainMapper) = mapper.map(photographers)
    }
    data class Fail(private val e: Exception) : PhotographerListData() {
        override fun map(mapper: PhotographerListDataToDomainMapper) = mapper.map(e)
    }

    object EmptyData : PhotographerListData() {
        override fun map(mapper: PhotographerListDataToDomainMapper) = mapper.map()
    }

}