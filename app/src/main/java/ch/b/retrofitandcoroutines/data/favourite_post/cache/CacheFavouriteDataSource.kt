package ch.b.retrofitandcoroutines.data.favourite_post.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDao
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud

interface CacheFavouriteDataSource {

    suspend fun saveFavouritePost(post: List<PhotographerData>)

    suspend fun readFavouritePost(): PhotographerListData

    suspend fun delete(id: Int)

    class Base(
        private val dao: PhotographerDao,
        private val mapperCache: PhotographerDataToDomainMapper<CachePhotographer.Base>,
        private val mapperData: Abstract.ToPhotographerMapper<PhotographerData>
    ) : CacheFavouriteDataSource {
        override suspend fun saveFavouritePost(post: List<PhotographerData>) {
            dao.insertFavouritesPost(post.map {
                it.map(mapperCache)
            })
        }

        override suspend fun readFavouritePost(): PhotographerListData {
            val data = dao.readFavouritePost().map {
                it.map(mapperData)
            }
            return if (data.isEmpty()) {
                PhotographerListData.EmptyData
            } else {
                PhotographerListData.Success(data)

            }
        }

        override suspend fun delete(id: Int) {
            dao.delete(id)
        }
    }
}