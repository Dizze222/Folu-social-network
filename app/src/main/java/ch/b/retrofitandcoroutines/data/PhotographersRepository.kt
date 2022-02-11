package ch.b.retrofitandcoroutines.data

import android.util.Log
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource
import kotlinx.coroutines.delay
import retrofit2.Response

//TODO refactor this because this work bed :/
interface PhotographersRepository {

    suspend fun getPhotographers(): PhotographersData
    suspend fun getDataFromServerAndSaveIntoDataBase(): PhotographersData
    suspend fun post(
        author: String,
        idPhotographer: Int,
        like: Int,
        theme: String,
        url: String
    ): Response<PhotographerCloud>

    class Base(
        private val cloudDataSource: PhotographersCloudDataSource,
        private val cacheDataSource: PhotographersCacheDataSource,
        private val photographersCloudMapper: PhotographersCloudMapper,
        private val photographersCacheMapper: PhotographersCacheMapper
    ) : PhotographersRepository {


        override suspend fun post(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ): Response<PhotographerCloud> {
            return cloudDataSource.makePost(author, idPhotographer, like, theme, url)
        }

        override suspend fun getDataFromServerAndSaveIntoDataBase(): PhotographersData {
            val photographerCloudList = cloudDataSource.getPhotographers()
            val photographersOfList = photographersCloudMapper.map(photographerCloudList)
            cacheDataSource.savePhotographers(photographersOfList)
            return PhotographersData.Success(photographersOfList)
        }

        private val photographerCacheList = cacheDataSource.getPhotographers()
        override suspend fun getPhotographers() = try {
            if (photographerCacheList.isEmpty()){
                getDataFromServerAndSaveIntoDataBase()
            }else{
                PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
            }
        } catch (e: Exception) {
            if (photographerCacheList.isNotEmpty()) {
                Log.i("TAG", "ветка catch")
                PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
            } else
                PhotographersData.Fail(e)
        }
    }
}