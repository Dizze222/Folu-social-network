package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import com.google.gson.annotations.SerializedName

//https://picsum.photos/v2/list
data class PhotographerCloud(
    private var id: Int,
    private var author: String,
) : Abstract.Object<PhotographerParameters, PhotographerCloudMapper>() {
    override fun map(mapper: PhotographerCloudMapper): PhotographerParameters {
       return mapper.map(id, author)
    }
}