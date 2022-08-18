package ch.b.retrofitandcoroutines.favourite_post.data.cache

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerListData
import ch.b.retrofitandcoroutines.all_posts.data.cache.CachePhotographer
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerDao
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper

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