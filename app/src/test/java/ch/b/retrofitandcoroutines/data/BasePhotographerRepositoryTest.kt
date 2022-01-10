package ch.b.retrofitandcoroutines.data

import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.data.cache.PhotographerCacheMapper
import ch.b.retrofitandcoroutines.data.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.net.PhotographerCloudMapper

abstract class BasePhotographerRepositoryTest {
    protected inner class TestPhotographerCacheMapper : PhotographerCacheMapper{
        override fun map(photographerDB: PhotographerDataBase): PhotographerParameters =
            PhotographerParameters(photographerDB.id,photographerDB.author,photographerDB.URL)
    }
    protected inner class TestPhotographerCloudMapper() : PhotographerCloudMapper{
        override fun map(id: Int, author: String, URL: String): PhotographerParameters  =
            PhotographerParameters(id,author,URL)
    }
}