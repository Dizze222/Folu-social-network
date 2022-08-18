package ch.b.retrofitandcoroutines.all_posts.data

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomain

sealed class PhotographerListData :
    Abstract.Object<PhotographerListDomain, PhotographerListDataToDomainMapper> {

    data class Success(private val photographers: List<PhotographerData>) : PhotographerListData() {
        override fun map(mapper: PhotographerListDataToDomainMapper): PhotographerListDomain =
            mapper.map(photographers)

    }

    data class Fail(private val e: String) : PhotographerListData() {
        override fun map(mapper: PhotographerListDataToDomainMapper) = mapper.map(e)
    }


    object EmptyData : PhotographerListData() {
        override fun map(mapper: PhotographerListDataToDomainMapper) = mapper.map()
    }

}