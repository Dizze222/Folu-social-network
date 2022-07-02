package ch.b.retrofitandcoroutines.data.all_posts.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud

interface PhotographerListCacheDataSource {

    suspend fun getPhotographers(): List<CachePhotographer>

    suspend fun savePhotographers(photographers: List<PhotographerCloud>)


    suspend fun searchPhotographers(author: String): List<CachePhotographer>

    class Base(
        private val dao: PhotographerDao,
        private val mapper: Abstract.ToPhotographerMapper<CachePhotographer.Base>
    ) : PhotographerListCacheDataSource {

        override suspend fun getPhotographers(): List<CachePhotographer> {
            return dao.readAllData()
        }

        override suspend fun savePhotographers(photographers: List<PhotographerCloud>) {
            dao.insert(photographers.map {
                it.map(mapper)
            })
        }


        override suspend fun searchPhotographers(author: String): List<CachePhotographer> =
            dao.searchDatabase(author)
    }
}