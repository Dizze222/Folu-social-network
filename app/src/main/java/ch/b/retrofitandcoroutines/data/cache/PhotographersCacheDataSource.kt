package ch.b.retrofitandcoroutines.data.cache

import ch.b.retrofitandcoroutines.data.PhotographerData
import ch.b.retrofitandcoroutines.data.mappers.PhotographerDataToDBMapper

interface PhotographersCacheDataSource {
    fun getPhotographers(): List<PhotographerDataBase>

    fun savePhotographers(photographers: List<PhotographerData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: PhotographerDataToDBMapper
    ) : PhotographersCacheDataSource {

        override fun getPhotographers(): List<PhotographerDataBase> {
            realmProvider.provide().use { realm ->
                val photographersDB = realm.where(PhotographerDataBase::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(photographersDB)
            }
        }

        override fun savePhotographers(photographers: List<PhotographerData>) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    photographers.forEach { photographer ->
                        photographer.mapTo(mapper, realm)
                    }
                }
            }
    }
}