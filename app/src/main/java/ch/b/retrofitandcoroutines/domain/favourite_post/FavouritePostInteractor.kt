package ch.b.retrofitandcoroutines.domain.favourite_post

import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.favourite_post.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomain

interface FavouritePostInteractor {





    class Base(
        private val dataSource: CacheFavouriteDataSource,
        private val mapper: PhotographerListDataToDomainMapper
    ) : FavouritePostInteractor {



    }

}