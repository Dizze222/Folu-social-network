package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerListUI
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class PhotographerListDomain : Abstract.Object<PhotographerListUI, PhotographerListDomainToUIMapper> {
    class Success(private val photographers: List<PhotographerData>, private val photographerMapper: PhotographerDataToDomainMapper) : PhotographerListDomain(){
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI {
            val photographersDomain = photographers.map {
                it.map(photographerMapper)
            }
            return mapper.map(photographersDomain)
        }
    }

    class Fail(private val e: Exception) : PhotographerListDomain(){
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI = mapper.map(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }

    object EmptyData : PhotographerListDomain() {
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI = mapper.map()

    }
}