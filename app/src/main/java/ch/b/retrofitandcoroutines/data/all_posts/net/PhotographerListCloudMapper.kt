package ch.b.retrofitandcoroutines.data.all_posts.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToPhotographerMapper

interface PhotographerListCloudMapper : Abstract.Mapper{
    fun map(cloudList: List<PhotographerCloud>) : List<PhotographerData>

    class Base(private val photographerCloudMapper: ToPhotographerMapper) :
        PhotographerListCloudMapper {
        override fun map(cloudList: List<PhotographerCloud>): List<PhotographerData> {
            return cloudList.map{photographerCLoud ->
                photographerCLoud.map(photographerCloudMapper)
            }
        }
    }
}