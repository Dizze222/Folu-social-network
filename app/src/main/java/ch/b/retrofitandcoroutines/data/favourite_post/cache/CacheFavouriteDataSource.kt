package ch.b.retrofitandcoroutines.data.favourite_post.cache

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDao
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud

interface CacheFavouriteDataSource {



    class Base(
        private val dao: PhotographerDao,
        private val mapper: Abstract.ToPhotographerMapper<PhotographerData>
    ) : CacheFavouriteDataSource {



    }
}