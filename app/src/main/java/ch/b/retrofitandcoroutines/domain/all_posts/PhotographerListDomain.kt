package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerListUI

sealed class PhotographerListDomain :
    Abstract.Object<PhotographerListUI, PhotographerListDomainToUIMapper> {
    class Success(
        private val photographers: List<PhotographerData>,
        private val photographerMapper: PhotographerDataToDomainMapper<PhotographerDomain>
    ) : PhotographerListDomain() {
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI {
            val photographersDomain = photographers.map {
                it.map(photographerMapper)
            }
            return mapper.map(photographersDomain)
        }
    }

    class Fail(private val error: String) : PhotographerListDomain() {
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI =
            mapper.map(error)
    }

    object EmptyData : PhotographerListDomain() {
        override fun map(mapper: PhotographerListDomainToUIMapper): PhotographerListUI =
            mapper.map()

    }
}