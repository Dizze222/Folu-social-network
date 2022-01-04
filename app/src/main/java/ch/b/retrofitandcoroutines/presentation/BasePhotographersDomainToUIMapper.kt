package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.domain.ErrorType
import ch.b.retrofitandcoroutines.domain.PhotographersDomainToUIMapper

class BasePhotographersDomainToUIMapper(
    private val communication: PhotographerCommunication,
    private val resourceProvider: ResourceProvider
) : PhotographersDomainToUIMapper {
    override fun map(photographers: List<PhotographerParameters>): PhotographersUI = PhotographersUI.Success(communication,photographers)

    override fun map(errorType: ErrorType): PhotographersUI = PhotographersUI.Fail(communication,errorType,resourceProvider)

}