package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographersCacheDataSource {
    fun getPhotographers() : List<PhotographerDB>

    fun savePhotographers(photographers: List<PhotographerParameters>)

    class Base(private val realmProvider: RealmProvider) : PhotographersCacheDataSource{
        override fun getPhotographers(): List<PhotographerDB> {
            realmProvider.provide().use { realm ->
                val photographersDB = realm.where(PhotographerDB::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(photographersDB)
            }
        }

        override fun savePhotographers(photographers: List<PhotographerParameters>) = realmProvider.provide().use { realm->
            realm.executeTransaction{
                photographers.forEach{ photographer ->
                    val photographerDB = it.createObject(PhotographerDB::class.java, photographer.id)
                    photographerDB.author = photographer.author
                    photographerDB.URL = photographer.URL
                }
            }
        }

    }



}