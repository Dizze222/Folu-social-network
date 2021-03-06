package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper

class BasePhotographerListDataToDomainMapper(
    private val photographerMapper: PhotographerDataToDomainMapper<PhotographerDomain>
) : PhotographerListDataToDomainMapper() {
    override fun map(photographers: List<PhotographerData>): PhotographerListDomain =
        PhotographerListDomain.Success(photographers, photographerMapper)

    override fun map(error: String): PhotographerListDomain = PhotographerListDomain.Fail(error)

    override fun map(): PhotographerListDomain = PhotographerListDomain.EmptyData

}
