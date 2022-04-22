package ch.b.retrofitandcoroutines.data


import ch.b.retrofitandcoroutines.data.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographerListCacheMapper
import ch.b.retrofitandcoroutines.data.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource
import retrofit2.Response


interface PhotographerRepository {

    suspend fun getPhotographers(): PhotographerListData

    suspend fun getDataFromServerAndSaveIntoDataBase(): PhotographerListData

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
        private val photographersCloudMapper: PhotographerListCloudMapper,
        private val photographersCacheMapper: PhotographerListCacheMapper
    ) : PhotographerRepository {


        override suspend fun post(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ): Response<PhotographerCloud> {
            return cloudDataSource.makePost(author, idPhotographer, like, theme, url)
        }

        override suspend fun getDataFromServerAndSaveIntoDataBase(): PhotographerListData {
            val photographerCloudList = cloudDataSource.getPhotographers()
            val photographersOfList = photographersCloudMapper.map(photographerCloudList)
            cacheDataSource.savePhotographers(photographersOfList)
            return PhotographerListData.Success(photographersOfList)
        }

        private val photographerCacheList = cacheDataSource.getPhotographers()

        override suspend fun getPhotographers() = try {
            val photographerCloudList = cloudDataSource.getPhotographers()

            if (photographerCloudList.size != photographerCacheList.size)
                cacheDataSource.deleteData()

            if (photographerCacheList.isEmpty())
                getDataFromServerAndSaveIntoDataBase()
            else
                PhotographerListData.Success(photographersCacheMapper.map(photographerCacheList))

            if (photographerCloudList.isEmpty())
                PhotographerListData.EmptyData
            else
                getDataFromServerAndSaveIntoDataBase()
        } catch (e: Exception) {
            if (photographerCacheList.isNotEmpty())
                PhotographerListData.Success(photographersCacheMapper.map(photographerCacheList))
            else
                PhotographerListData.Fail(e)
        }
    }
}