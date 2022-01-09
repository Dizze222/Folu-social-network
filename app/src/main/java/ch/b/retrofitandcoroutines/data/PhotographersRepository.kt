package ch.b.retrofitandcoroutines.data

import android.util.Log
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersDataSource

interface PhotographersRepository {
    suspend fun getPhotographers() : PhotographersData
    class Base(
        private val cloudDataSource: PhotographersDataSource,
        private val cacheDataSource: PhotographersCacheDataSource,
        private val photographersCloudMapper: PhotographersCloudMapper,
        private val photographersCacheMapper: PhotographersCacheMapper) : PhotographersRepository{
        private suspend fun getDataFromServerAndSaveIntoDataBase() : PhotographersData{
            val photographerCloudList = cloudDataSource.getPhotographers()
            val photographers = photographersCloudMapper.map(photographerCloudList)
            cacheDataSource.savePhotographers(photographers)
            return PhotographersData.Success(photographers)
        }
        override suspend fun getPhotographers() : PhotographersData{
            try {
                val photographerCacheList = cacheDataSource.getPhotographers()
                if (cloudDataSource.getPhotographers().size > 30){
                    getDataFromServerAndSaveIntoDataBase()
                }
                return if (photographerCacheList.isEmpty()){
                    getDataFromServerAndSaveIntoDataBase()
                }else{
                    PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
                }
            }catch (e: Exception){
                val photographerCacheList = cacheDataSource.getPhotographers()
                if (photographerCacheList.isNotEmpty()){
                    return PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
                }else{
                    return PhotographersData.Fail(e)
                }
            }
        }
    }
}