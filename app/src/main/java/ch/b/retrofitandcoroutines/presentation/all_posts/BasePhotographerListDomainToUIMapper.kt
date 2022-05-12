package ch.b.retrofitandcoroutines.presentation.all_posts

import ch.b.retrofitandcoroutines.domain.all_posts.ErrorType
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper

class BasePhotographerListDomainToUIMapper(
    private val photographerMapper: PhotographerDomainToUIMapper,
    private val resourceProvider: ResourceProvider
) : PhotographerListDomainToUIMapper() {
    override fun map(data: List<PhotographerDomain>): PhotographerListUI =
        PhotographerListUI.Success(data, photographerMapper)

    override fun map(errorType: ErrorType): PhotographerListUI =
        PhotographerListUI.Fail(errorType, resourceProvider)

    override fun map(): PhotographerListUI = PhotographerListUI.EmptyData

}