package ch.b.retrofitandcoroutines.presentation.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.core.PhotographerCommunication

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