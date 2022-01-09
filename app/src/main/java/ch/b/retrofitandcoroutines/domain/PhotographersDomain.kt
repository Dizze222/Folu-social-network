package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.presentation.PhotographersUI
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class PhotographersDomain : Abstract.Object<PhotographersUI,PhotographersDomainToUIMapper>() {
    class Success(private val photographers: List<PhotographerParameters>) : PhotographersDomain(){
        override fun map(mapper: PhotographersDomainToUIMapper): PhotographersUI {
           return mapper.map(photographers)
        }
    }

    class Fail(private val e: Exception) : PhotographersDomain(){
        override fun map(mapper: PhotographersDomainToUIMapper): PhotographersUI = mapper.map(
            when(e){
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}