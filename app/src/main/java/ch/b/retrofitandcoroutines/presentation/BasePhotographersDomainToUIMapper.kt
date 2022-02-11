package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.domain.*

class BasePhotographersDomainToUIMapper(
    private val photographerMapper: PhotographerDomainToUIMapper,
    private val resourceProvider: ResourceProvider
) : PhotographersDomainToUIMapper {
    override fun map(photographers: List<PhotographerDomain>): PhotographersUI = PhotographersUI.Success(photographers, photographerMapper)

    override fun map(errorType: ErrorType): PhotographersUI = PhotographersUI.Fail(errorType,resourceProvider)

}