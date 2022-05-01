package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import io.realm.RealmList

interface ToPhotographerMapper : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: RealmList<String>,
        authorOfComments: RealmList<String>
    ): PhotographerData

    class Base : ToPhotographerMapper {
        override fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: RealmList<String>,
            authorOfComments: RealmList<String>
        ) =
            PhotographerData(id, author, URL, like, theme, comments,authorOfComments)
    }
}