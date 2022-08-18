package ch.b.retrofitandcoroutines.all_posts.domain

import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper

class BasePhotographerListDataToDomainMapper(
    private val photographerMapper: PhotographerDataToDomainMapper<PhotographerDomain>
) : PhotographerListDataToDomainMapper() {
    override fun map(photographers: List<PhotographerData>): PhotographerListDomain =
        PhotographerListDomain.Success(photographers, photographerMapper)

    override fun map(error: String): PhotographerListDomain = PhotographerListDomain.Fail(error)

    override fun map(): PhotographerListDomain = PhotographerListDomain.EmptyData

}
