package ch.b.retrofitandcoroutines.data.all_posts.cache

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDBMapper

interface PhotographerListCacheDataSource {
    fun getPhotographers(): List<PhotographerDataBase>

    fun savePhotographers(photographers: List<PhotographerData>)

    fun deleteData()

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: PhotographerDataToDBMapper
    ) : PhotographerListCacheDataSource {

        override fun getPhotographers(): List<PhotographerDataBase> {
            realmProvider.provide().use { realm ->
                val photographersDB = realm.where(PhotographerDataBase::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(photographersDB)
            }
        }

        override fun savePhotographers(photographers: List<PhotographerData>) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    it.deleteAll()
                    photographers.forEach { photographer ->
                        photographer.mapTo(mapper, realm)
                    }
                }
            }

        override fun deleteData() {
            realmProvider.provide().use { realm->
                realm.executeTransaction {
                    it.deleteAll()
                }
            }
        }
    }
}