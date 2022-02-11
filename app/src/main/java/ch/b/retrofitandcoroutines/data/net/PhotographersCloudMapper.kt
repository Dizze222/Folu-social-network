package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper

interface PhotographersCloudMapper : Abstract.Mapper{
    fun map(cloudList: List<PhotographerCloud>) : List<PhotographerData>

    class Base(private val photographerCloudMapper: ToPhotographerMapper) : PhotographersCloudMapper{
        override fun map(cloudList: List<PhotographerCloud>): List<PhotographerData> {
            return cloudList.map{photographerCLoud ->
                photographerCLoud.map(photographerCloudMapper)
            }
        }
    }
}