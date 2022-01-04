package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

//https://picsum.photos/v2/list
data class PhotographerCloud(
    private val id: Int,
    private val author: String,
) : Abstract.Object<PhotographerParameters, PhotographerCloudMapper>() {
    override fun map(mapper: PhotographerCloudMapper): PhotographerParameters {
       return mapper.map(id, author)
    }
}