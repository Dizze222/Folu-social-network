package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.domain.ErrorType

sealed class PhotographersUI : Abstract.Object<Unit,Abstract.Mapper.Empty>(){
    class Success(
        private val communication: PhotographerCommunication,
        private val photographer: List<PhotographerParameters>
    ) : PhotographersUI(){
        override fun map(mapper: Abstract.Mapper.Empty) = communication.show(photographer)
    }

    class Fail(
        private val communication: PhotographerCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : PhotographersUI(){
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when(errorType){
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                else -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }

    }

}