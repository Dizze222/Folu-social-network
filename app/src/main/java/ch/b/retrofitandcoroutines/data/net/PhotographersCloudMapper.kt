package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographersCloudMapper : Abstract.Mapper{
    fun map(cloudList: List<PhotographerCloud>) : List<PhotographerParameters>

    class Base(private val photographerCloudMapper: PhotographerCloudMapper) : PhotographersCloudMapper{
        override fun map(cloudList: List<PhotographerCloud>): List<PhotographerParameters> {
            return cloudList.map{photographerCLoud ->
                photographerCLoud.map(photographerCloudMapper)
            }
        }

    }

}