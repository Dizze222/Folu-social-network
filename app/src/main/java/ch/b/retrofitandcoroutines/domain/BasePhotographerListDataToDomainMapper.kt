package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.mappers.PhotographerListDataToDomainMapper

class BasePhotographerListDataToDomainMapper(
    private val photographerMapper: PhotographerDataToDomainMapper
) : PhotographerListDataToDomainMapper {
    override fun map(photographers: List<PhotographerData>,): PhotographerListDomain =
        PhotographerListDomain.Success(photographers,photographerMapper)

    override fun map(e: Exception): PhotographerListDomain = PhotographerListDomain.Fail(e)

    override fun map(): PhotographerListDomain = PhotographerListDomain.EmptyData

}