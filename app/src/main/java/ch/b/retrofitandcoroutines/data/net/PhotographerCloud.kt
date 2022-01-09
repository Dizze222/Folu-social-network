package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import com.google.gson.annotations.SerializedName

//https://picsum.photos/v2/list
data class PhotographerCloud(
    private val id: Int,
    private val author: String,
    @SerializedName("download_url")
    private val URL: String
) : Abstract.Object<PhotographerParameters, PhotographerCloudMapper>() {
    override fun map(mapper: PhotographerCloudMapper): PhotographerParameters {
       return mapper.map(id, author,URL)
    }
}