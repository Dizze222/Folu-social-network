package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.BaseToDataPhotographerMapper
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudDataSource
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

class PhotographerRepositoryTest : BasePhotographerRepositoryTest() {

    private val unknownHostException = Exception()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestPhotographerListCloudDataSource(false)
        val testCacheDataSource = TestPhotographerListCacheDataSource(false)
        val repository = PhotographerRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographerListCloudMapper.Base(TestToBookMapper()),
            BaseToDataPhotographerMapper(),
            ExceptionPostsMapper.Test()
        )
        val actual = repository.getAllPhotographers()
        val expected = PhotographerListData.Fail("Что-то пошло не так")
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestPhotographerListCloudDataSource(true)
        val testCacheDataSource = TestPhotographerListCacheDataSource(false)
        val repository = PhotographerRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographerListCloudMapper.Base(TestToBookMapper()),
            BaseToDataPhotographerMapper(),
            ExceptionPostsMapper.Test()
        )
        val actual = repository.getAllPhotographers()
        val expected = PhotographerListData.Success(
            listOf(
                PhotographerData.Base(1, "name1", "url", 1, "theme1", listOf("one"), listOf("one")),
                PhotographerData.Base(2, "name2", "url", 2, "theme2", listOf("two"), listOf("two")),
                PhotographerData.Base(3, "name3", "url", 3, "theme3", listOf("third"), listOf("third"))
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking{
        val testCloudDataSource = TestPhotographerListCloudDataSource(true)
        val testCacheDataSource = TestPhotographerListCacheDataSource(true)
        val repository = PhotographerRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            PhotographerListCloudMapper.Base(TestToBookMapper()),
            BaseToDataPhotographerMapper(),
            ExceptionPostsMapper.Test()
        )
        val actual = repository.getAllPhotographers()
        val expected = PhotographerListData.Success(listOf(
                PhotographerData.Base(
                    10,
                    "name10",
                    "url",
                    10,
                    "theme10",
                    listOf("ten"),
                    listOf("ten")
                ),
            PhotographerData.Base(
                20,
                "name20",
                "url",
                20,
                "theme20",
                listOf("twelve"),
                listOf("twelve")
            ),
            PhotographerData.Base(
                30,
                "name30",
                "url",
                30,
                "theme30",
                listOf("third"),
                listOf("third")
            )
        ))
        assertEquals(expected,actual)
    }


    private inner class TestPhotographerListCloudDataSource(private val returnSuccess: Boolean) :
        PhotographerListCloudDataSource {
        override suspend fun getPhotographers(): List<PhotographerCloud.Base> {
            if (returnSuccess) {
                return listOf(
                    PhotographerCloud.Base(
                        1,
                        "name1",
                        "url",
                        1,
                        "theme1",
                        listOf("one"),
                        listOf("one")
                    ),
                    PhotographerCloud.Base(
                        2,
                        "name2",
                        "url",
                        2,
                        "theme2",
                        listOf("two"),
                        listOf("two")
                    ),
                    PhotographerCloud.Base(
                        3,
                        "name3",
                        "url",
                        3,
                        "theme3",
                        listOf("third"),
                        listOf("third")
                    )
                )
            } else {
                throw unknownHostException
            }
        }

        override suspend fun like(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ): Response<PhotographerCloud> {
            TODO("Not yet implemented")
        }



    }


    private inner class TestPhotographerListCacheDataSource(
        private val returnSuccess: Boolean
    ) : PhotographerListCacheDataSource {
        override suspend fun getPhotographers(): List<CachePhotographer> {
            if (returnSuccess) {
                return listOf(
                    CachePhotographer.Base(
                        10,
                        "name10",
                        "url",
                        10,
                        "theme10",
                        listOf("ten"),
                        listOf("ten")
                    ), CachePhotographer.Base(
                        20,
                        "name20",
                        "url",
                        20,
                        "theme20",
                        listOf("twelve"),
                        listOf("twelve")
                    ), CachePhotographer.Base(
                        30,
                        "name30",
                        "url",
                        30,
                        "theme30",
                        listOf("third"),
                        listOf("third")
                    )

                )
            } else {
                return emptyList<CachePhotographer.Base>()
            }
        }

        override suspend fun savePhotographers(photographers: List<PhotographerCloud>) {
            //not used here
        }

        override suspend fun delete() {
            TODO("Not yet implemented")
        }

        override suspend fun searchPhotographers(author: String): List<CachePhotographer> {
            TODO("Not yet implemented")
        }

    }

}