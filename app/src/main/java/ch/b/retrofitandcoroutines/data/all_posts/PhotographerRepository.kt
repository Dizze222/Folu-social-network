package ch.b.retrofitandcoroutines.data.all_posts


import android.util.Log
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudDataSource
import retrofit2.Response
import java.lang.Exception


interface PhotographerRepository {

    suspend fun getAllPhotographers(): PhotographerListData

    suspend fun searchPhotographers(author: String): PhotographerListData

    suspend fun like(
        author: String,
        idPhotographer: Int,
        like: Int,
        theme: String,
        url: String
    ): Response<PhotographerCloud>

    class Base(
        private val cloudDataSource: PhotographerListCloudDataSource,
        private val cacheDataSource: PhotographerListCacheDataSource,
        private val cloudMapper: PhotographerListCloudMapper,
        private val mapperData: Abstract.ToPhotographerMapper<PhotographerData>,
        private val exceptionMapper: ExceptionPostsMapper
    ) : PhotographerRepository {

        override suspend fun getAllPhotographers() = try {
            val listOfCloud = cloudDataSource.getPhotographers()
            val listOfCache = cacheDataSource.getPhotographers()
            if (listOfCache.isEmpty()) {
                val photographerOfList = cloudMapper.map(listOfCloud)
                cacheDataSource.savePhotographers(listOfCloud)
                PhotographerListData.Success(photographerOfList)
            } else {
                PhotographerListData.Success(listOfCloud.map {
                    it.map(mapperData)
                }
                )
            }

        } catch (e: Exception) {
            val messageError = exceptionMapper.mapper(e)
            PhotographerListData.Fail(messageError)
        }

        override suspend fun searchPhotographers(author: String): PhotographerListData {
            return PhotographerListData.Success(cacheDataSource.searchPhotographers(author).map {
                it.map(mapperData)
            })
        }

        override suspend fun like(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ): Response<PhotographerCloud> {
            return cloudDataSource.makePost(author, idPhotographer, like, theme, url)
        }
    }
}
