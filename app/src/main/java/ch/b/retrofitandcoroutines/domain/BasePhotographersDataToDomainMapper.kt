package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.data.PhotographersDataToDomainMapper

class BasePhotographersDataToDomainMapper : PhotographersDataToDomainMapper {
    override fun map(photographers: List<PhotographerParameters>): PhotographersDomain =
        PhotographersDomain.Success(photographers)

    override fun map(e: Exception): PhotographersDomain = PhotographersDomain.Fail(e)

}