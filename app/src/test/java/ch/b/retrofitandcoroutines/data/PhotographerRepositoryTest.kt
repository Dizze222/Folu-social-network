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
import java.io.IOException
import java.net.UnknownHostException

class PhotographerRepositoryTest : BasePhotographerRepositoryTest(){
    /*
    private val unknownHostException = UnknownHostException()
    private companion object{
        const val testURL = "https://i.picsum.photos/id/0/5616/3744.jpg?hmac=3GAAioiQziMGEtLbfrdbcoenXoWAW-zlyEAMkfEdBzQ"
    }

    @Test
    fun test_no_connection_no_cache() = runBlocking{
        val testCloudDataSource = TestCLoudDataSource(false)
        val testCacheDataSource = TestCacheDataSource(false)
        val repository = PhotographersRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographersCloudMapper.Base(TestPhotographerCloudMapper()),
            PhotographersCacheMapper.Base(TestPhotographerCacheMapper()))
        val actual = repository.getPhotographers()
        val expected = PhotographersData.Fail(unknownHostException)
        assertEquals(actual, expected)
    }
    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestCLoudDataSource(true)
        val testCacheDataSource = TestCacheDataSource(false)
        val repository = PhotographersRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographersCloudMapper.Base(TestPhotographerCloudMapper()),
            PhotographersCacheMapper.Base(TestPhotographerCacheMapper()))
        val actual = repository.getPhotographers()
        val expected = PhotographersData.Success(listOf(
            PhotographerParameters(0,"author0", testURL,1),
            PhotographerParameters(1,"author1", testURL,1),
            PhotographerParameters(2,"author2", testURL,1)
        ))
        assertEquals(actual,expected)
    }
    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCLoudDataSource(false)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = PhotographersRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographersCloudMapper.Base(TestPhotographerCloudMapper()),
            PhotographersCacheMapper.Base(TestPhotographerCacheMapper()))
        val actual = repository.getPhotographers()
        val expected = PhotographersData.Success(
            listOf(
                PhotographerParameters(10,"author10", testURL,1),
                PhotographerParameters(11,"author11", testURL,1),
                PhotographerParameters(12,"author12", testURL,1)
            ))
        assertEquals(expected,actual)
    }
    @Test
    fun test_with_connection_and_cache() = runBlocking {
        val testCloudDataSource = TestCLoudDataSource(true)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = PhotographersRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographersCloudMapper.Base(TestPhotographerCloudMapper()),
            PhotographersCacheMapper.Base(TestPhotographerCacheMapper()))
        val actual = repository.getPhotographers()
        val expected = PhotographersData.Success(
            listOf(
                PhotographerParameters(10,"author10", testURL,1),
                PhotographerParameters(11,"author11", testURL,1),
                PhotographerParameters(12,"author12", testURL,1)
            )
        )
        assertEquals(actual,expected)
    }

    private inner class TestCLoudDataSource(
        private val returnSuccess: Boolean,
        private val errorTypeNoConnection: Boolean = true) : PhotographersCloudDataSource{
        override suspend fun getPhotographers(): List<PhotographerCloud> {
            if (returnSuccess){
                return listOf(
                    PhotographerCloud(0,"author0", testURL,1),
                    PhotographerCloud(1,"author1", testURL,1),
                    PhotographerCloud(2,"author2", testURL,1)
                )
            }else{
                if (errorTypeNoConnection)
                    throw unknownHostException
                else
                    throw IOException()
            }
        }
    }
    private inner class TestCacheDataSource(
        private val returnSuccess: Boolean
        ) : PhotographersCacheDataSource{
        override fun getPhotographers(): List<PhotographerDataBase> {
         return if (returnSuccess){
                listOf(
                    PhotographerDataBase().apply {
                        id = 10
                        author = "author10"
                        URL = testURL
                    },
                    PhotographerDataBase().apply {
                        id = 11
                        author = "author11"
                        URL = testURL
                    },
                    PhotographerDataBase().apply {
                        id = 12
                        author = "author12"
                        URL = testURL
                    }
                )
            }else {
                emptyList()
            }
        }

        override fun savePhotographers(photographers: List<PhotographerParameters>) {

        }

        override fun deleteAllData() {
            TODO("Not yet implemented")
        }

    }

     */
}