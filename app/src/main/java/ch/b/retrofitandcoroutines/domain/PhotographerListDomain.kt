package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.PhotographerListUI
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class PhotographerListDomain : Abstract.Object<PhotographerListUI,PhotographersDomainToUIMapper> {
    class Success(private val photographers: List<PhotographerData>,private val photographerMapper: PhotographerDataToDomainMapper) : PhotographerListDomain(){
        override fun map(mapper: PhotographersDomainToUIMapper): PhotographerListUI {
            val photographersDomain = photographers.map {
                it.map(photographerMapper)
            }
            return mapper.map(photographersDomain)
        }
    }

    class Fail(private val e: Exception) : PhotographerListDomain(){
        override fun map(mapper: PhotographersDomainToUIMapper): PhotographerListUI = mapper.map(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }

    object EmptyData : PhotographerListDomain() {
        override fun map(mapper: PhotographersDomainToUIMapper): PhotographerListUI = mapper.map()

    }
}