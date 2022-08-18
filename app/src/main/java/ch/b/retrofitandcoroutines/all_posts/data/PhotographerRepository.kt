package ch.b.retrofitandcoroutines.all_posts.data


import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.all_posts.data.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerCloud
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerListCloudDataSource
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
        private val mapperData: Abstract.ToPhotographerMapper<PhotographerData>,
        private val exceptionMapper: ExceptionPostsMapper
    ) : PhotographerRepository {

        override suspend fun getAllPhotographers() = try {
            val listOfCloud = cloudDataSource.getPhotographers()
            if (listOfCloud.isEmpty())
                PhotographerListData.EmptyData
            else
                PhotographerListData.Success(listOfCloud.map { it.map(mapperData) })
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
            return cloudDataSource.like(author, idPhotographer, like, theme, url)
        }
    }
}
