package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.ToPhotographerMapper
import com.google.gson.annotations.SerializedName

//https://picsum.photos/v2/list
data class PhotographerCloud(
    @SerializedName("idPhotographer")
    private val id: Int,
    private val author: String,
    @SerializedName("url")
    private val URL: String,
    private val like: Long,
    private val theme: String
) : Abstract.Object<PhotographerData, ToPhotographerMapper> {
    override fun map(mapper: ToPhotographerMapper): PhotographerData =
        mapper.map(id, author, URL, like, theme)

}