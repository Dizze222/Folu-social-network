package ch.b.retrofitandcoroutines.all_posts.presentation

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomain
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication

sealed class PhotographerListUI : Abstract.Object<Unit, PhotographerCommunication> {
    class Success(
        private val photographers: List<PhotographerDomain>,
        private val photographerMapper: PhotographerDomainToUIMapper<PhotographerUI>
    ) : PhotographerListUI() {
        override fun map(mapper: PhotographerCommunication) {
            val photographersUI = photographers.map {
                it.map(photographerMapper)
            }
            mapper.map(photographersUI)
        }

    }

    class Fail(
        private val message: String
    ) : PhotographerListUI() {
        override fun map(mapper: PhotographerCommunication) {
            mapper.map(listOf(PhotographerUI.Fail(message)))
        }

    }

    object EmptyData : PhotographerListUI() {
        override fun map(mapper: PhotographerCommunication) =
            mapper.map(listOf(PhotographerUI.EmptyData))
    }

}