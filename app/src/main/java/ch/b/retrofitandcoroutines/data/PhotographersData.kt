package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.domain.PhotographersDomain
import java.lang.Exception

sealed class PhotographersData : Abstract.Object<PhotographersDomain,PhotographersDataToDomainMapper>() {
    class Success(private val photographers: List<PhotographerParameters>) : PhotographersData(){
        override fun map(mapper: PhotographersDataToDomainMapper) = mapper.map(photographers)
    }
    class Fail(private val e: Exception) : PhotographersData() {
        override fun map(mapper:PhotographersDataToDomainMapper):PhotographersDomain =mapper.map(e)
    }


}