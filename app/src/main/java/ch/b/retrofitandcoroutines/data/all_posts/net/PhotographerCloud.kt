package ch.b.retrofitandcoroutines.data.all_posts.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToPhotographerMapper
import com.google.gson.annotations.SerializedName
import io.realm.RealmList

data class PhotographerCloud(
    @SerializedName("idPhotographer")
     val id: Int,
     val author: String,
    @SerializedName("url")
     val URL: String,
     val like: Long,
     val theme: String,
     val comments: RealmList<String>,
     val authorOfComments: RealmList<String>,
) : Abstract.Object<PhotographerData, ToPhotographerMapper> {
    override fun map(mapper: ToPhotographerMapper): PhotographerData =
        mapper.map(id, author, URL, like, theme,comments,authorOfComments)
}