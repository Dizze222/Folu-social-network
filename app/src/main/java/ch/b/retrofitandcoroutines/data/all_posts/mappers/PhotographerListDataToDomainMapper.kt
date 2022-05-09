package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomain

interface PhotographerListDataToDomainMapper : Abstract.Mapper {
    fun map(photographers: List<PhotographerData>) : PhotographerListDomain

    fun map(e: Exception) : PhotographerListDomain

  //  fun map() : PhotographerListDomain
}