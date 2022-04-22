package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.domain.*

class BasePhotographerListDomainToUIMapper(
    private val photographerMapper: PhotographerDomainToUIMapper,
    private val resourceProvider: ResourceProvider
) : PhotographersDomainToUIMapper {
    override fun map(photographers: List<PhotographerDomain>): PhotographerListUI = PhotographerListUI.Success(photographers, photographerMapper)

    override fun map(errorType: ErrorType): PhotographerListUI = PhotographerListUI.Fail(errorType,resourceProvider)

    override fun map(): PhotographerListUI = PhotographerListUI.EmptyData

}