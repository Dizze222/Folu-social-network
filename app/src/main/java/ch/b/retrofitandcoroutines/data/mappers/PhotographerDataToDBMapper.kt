package ch.b.retrofitandcoroutines.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.cache.PhotographerDataBase
import io.realm.Realm
import io.realm.RealmList

interface PhotographerDataToDBMapper : Abstract.Mapper {
    fun map(id: Int, author: String, URL: String, like: Long, theme: String, realm: Realm,comments: RealmList<String>,authorOfComments: RealmList<String>)
    : PhotographerDataBase

    class Base : PhotographerDataToDBMapper {
        override fun map(
            id: Int, author: String, URL: String, like: Long, theme: String, realm: Realm,comments: RealmList<String>,authorOfComments: RealmList<String>
        ): PhotographerDataBase {
            val photographerDB = realm.createObject(PhotographerDataBase::class.java, id)
            photographerDB.author = author
            photographerDB.URL = URL
            photographerDB.like = like
            photographerDB.theme = theme
            photographerDB.comments = comments
            photographerDB.authorOfComments = authorOfComments
            return photographerDB
        }
    }
}