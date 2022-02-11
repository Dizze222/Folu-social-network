package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.data.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheDataSource
import ch.b.retrofitandcoroutines.data.cache.PhotographersCacheMapper
import ch.b.retrofitandcoroutines.data.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.net.PhotographersCloudMapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PhotographerRepositorySaveTest : BasePhotographerRepositoryTest(){
    /*

    companion object{
        const val testURL = "https://i.picsum.photos/id/0/5616/3744.jpg?hmac=3GAAioiQziMGEtLbfrdbcoenXoWAW-zlyEAMkfEdBzQ"
    }

    @Test
    fun test_save_photographers() = runBlocking{
        val testCloudDataSource = TestCLoudDataSource()
        val testCacheDataSource = TestCacheDataSource()
        val repository = PhotographersRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographersCloudMapper.Base(TestPhotographerCloudMapper()),
            PhotographersCacheMapper.Base(TestPhotographerCacheMapper()))
        val actualCloud = repository.getPhotographers()
        val expectedCloud = PhotographersData.Success(listOf(
            PhotographerParameters(0,"author0", testURL,1),
            PhotographerParameters(1,"author1", testURL,1),
        ))
        assertEquals(expectedCloud,actualCloud)

        val actualCache = repository.getPhotographers()
        val expectedCache = PhotographersData.Success(listOf(
            PhotographerParameters(0,"author0 db", testURL,1),
            PhotographerParameters(1,"author1 db", testURL,1)
        ))
        assertEquals(expectedCache,actualCache)
    }
    private inner class TestCLoudDataSource : PhotographersCloudDataSource{
        override suspend fun getPhotographers(): List<PhotographerCloud> {
            return listOf(
                PhotographerCloud(0,"author0", testURL,1),
                PhotographerCloud(1,"author1", testURL,1),
            )
        }
    }
    private inner class TestCacheDataSource : PhotographersCacheDataSource{
        private val list = ArrayList<PhotographerDataBase>()
        override fun getPhotographers() = list

        override fun savePhotographers(photographers: List<PhotographerParameters>) {
            photographers.map { photogrpaher ->
                list.add(PhotographerDataBase().apply {
                    this.id = photogrpaher.id
                    this.author = "${photogrpaher.author} db"
                    this.URL = photogrpaher.URL
                })

            }
        }

        override fun deleteAllData() {
            TODO("Not yet implemented")
        }

    }


     */
}