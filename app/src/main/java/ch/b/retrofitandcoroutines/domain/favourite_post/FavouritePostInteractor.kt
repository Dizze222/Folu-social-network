package ch.b.retrofitandcoroutines.domain.favourite_post

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.favourite_post.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomain

interface FavouritePostInteractor {


    suspend fun saveFavouritePost(post: List<PhotographerDomain>)

    suspend fun readFavouritePost(): PhotographerListDomain


    class Base(
        private val dataSource: CacheFavouriteDataSource,
        private val mapper: PhotographerListDataToDomainMapper,
        private val mapperData: PhotographerDomainToUIMapper<PhotographerData>
    ) : FavouritePostInteractor {
        override suspend fun saveFavouritePost(post: List<PhotographerDomain>) {
            dataSource.saveFavouritePost(post.map {
                it.map(mapperData)
            })
        }

        override suspend fun readFavouritePost(): PhotographerListDomain =
            dataSource.readFavouritePost().map(mapper)


    }

}