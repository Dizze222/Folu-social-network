package ch.b.retrofitandcoroutines.data.all_posts


import android.util.Log
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ToRoomMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import retrofit2.Response
import java.lang.Exception


interface PhotographerRepository {

    suspend fun getAllPhotographers(): PhotographerListData

    suspend fun searchPhotographers(author: String): PhotographerListData

    suspend fun post(
        author: String,
        idPhotographer: Int,
        like: Int,
        theme: String,
        url: String
    ): Response<PhotographerCloud>

    class Base(
        private val cloudDataSource: PhotographersCloudDataSource,
        private val cacheDataSource: PhotographerListCacheDataSource,
        private val cloudMapper: PhotographerListCloudMapper,
        private val toRoomMapper: ToRoomMapper
    ) : PhotographerRepository {

        override suspend fun getAllPhotographers() = try {
            val listOfCache = cacheDataSource.getPhotographers()
            if (listOfCache.isEmpty()) {
                val listOfCloud = cloudDataSource.getPhotographers()
                Log.i("TOR", listOfCloud.toString())
                val photographerOfList = cloudMapper.map(listOfCloud)
                cacheDataSource.savePhotographers(listOfCloud)
                PhotographerListData.Success(photographerOfList)
            } else {
                Log.i("CAA", listOfCache.toString())
                PhotographerListData.Success(listOfCache.map {
                    it.map(toRoomMapper)
                })
            }

        } catch (e: Exception) {
            Log.i("TAA", e.toString())
            PhotographerListData.Fail(e)
        }

        override suspend fun searchPhotographers(author: String): PhotographerListData {
            return PhotographerListData.Success(cacheDataSource.searchPhotographers(author).map {
                it.map(toRoomMapper)
            })
        }

        override suspend fun post(
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
