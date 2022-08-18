package ch.b.retrofitandcoroutines.favourite_post.domain

import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.favourite_post.data.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomain
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomain

interface FavouritePostInteractor {


    suspend fun saveFavouritePost(post: List<PhotographerDomain>)

    suspend fun readFavouritePost(): PhotographerListDomain

    suspend fun delete(id: Int)
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

        override suspend fun delete(id: Int) {
            dataSource.delete(id)
        }


    }

}