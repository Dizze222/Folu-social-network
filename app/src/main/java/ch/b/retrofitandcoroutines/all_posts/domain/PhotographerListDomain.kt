package ch.b.retrofitandcoroutines.all_posts.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerListUI

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