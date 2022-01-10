package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource

interface PhotographersRepository {
    suspend fun getPhotographers() : PhotographersData
    suspend fun getDataFromServerAndSaveIntoDataBase() : PhotographersData
    class Base(
        private val cloudDataSource: PhotographersCloudDataSource,
        private val cacheDataSource: PhotographersCacheDataSource,
        private val photographersCloudMapper: PhotographersCloudMapper,
        private val photographersCacheMapper: PhotographersCacheMapper) : PhotographersRepository{
        override suspend fun getDataFromServerAndSaveIntoDataBase(): PhotographersData {
            val photographerCloudList = cloudDataSource.getPhotographers()
            val photographers = photographersCloudMapper.map(photographerCloudList)
            cacheDataSource.savePhotographers(photographers)
            return PhotographersData.Success(photographers)
        }
        private val photographerCacheList = cacheDataSource.getPhotographers()
        override suspend fun getPhotographers() : PhotographersData{
            try {
                if (cloudDataSource.getPhotographers().size > 30)
                    getDataFromServerAndSaveIntoDataBase()
                return if (photographerCacheList.isEmpty())
                    getDataFromServerAndSaveIntoDataBase()
                else
                    PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
            }catch (e: Exception){
                if (photographerCacheList.isNotEmpty())
                    return PhotographersData.Success(photographersCacheMapper.map(photographerCacheList))
                else
                    return PhotographersData.Fail(e)
            }
        }
    }
}