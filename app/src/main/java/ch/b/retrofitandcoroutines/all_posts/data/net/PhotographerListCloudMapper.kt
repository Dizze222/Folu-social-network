package ch.b.retrofitandcoroutines.all_posts.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData

interface PhotographerListCloudMapper : Abstract.Mapper{
    fun map(cloudList: List<PhotographerCloud>) : List<PhotographerData>

    class Base(private val mapper: Abstract.ToPhotographerMapper<PhotographerData>) :
        PhotographerListCloudMapper {
        override fun map(cloudList: List<PhotographerCloud>): List<PhotographerData> {
            return cloudList.map{photographerCLoud ->
                photographerCLoud.map(mapper)
            }
        }
    }
}