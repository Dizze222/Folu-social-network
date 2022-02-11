package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.mappers.PhotographersDataToDomainMapper

class BasePhotographersDataToDomainMapper(
    private val photographerMapper: PhotographerDataToDomainMapper
) : PhotographersDataToDomainMapper {
    override fun map(photographers: List<PhotographerData>,): PhotographersDomain =
        PhotographersDomain.Success(photographers,photographerMapper)

    override fun map(e: Exception): PhotographersDomain = PhotographersDomain.Fail(e)

}