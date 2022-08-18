package ch.b.retrofitandcoroutines.di.module

import android.content.Context
import androidx.room.Room
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.FeatureScope
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.cache.CachePhotographer
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerDao
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.all_posts.data.mappers.BaseToCachePhotographerMapper
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.core.Reader
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.favourite_post.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.data.favourite_post.mappers.BasePhotographerDataToCacheMapper
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    private companion object {
        private const val DATABASE_NAME = "photographer_db"
    }


    @Provides
    @FeatureScope
    fun providePhotographerDatabase(context: Context): PhotographerDataBase {
        return Room.databaseBuilder(
            context,
            PhotographerDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @FeatureScope
    fun providePhotographerDao(photographerDatabase: PhotographerDataBase): PhotographerDao {
        return photographerDatabase.photographerDao()
    }

    @Provides
    @FeatureScope
    fun provideCacheDataSource(
        dao: PhotographerDao,
        mapper: Abstract.ToPhotographerMapper<CachePhotographer.Base>
    ): PhotographerListCacheDataSource {
        return PhotographerListCacheDataSource.Base(dao, mapper)
    }

    @Provides
    @FeatureScope
    fun provideToCachePhotographerMapper(): Abstract.ToPhotographerMapper<CachePhotographer.Base> {
        return BaseToCachePhotographerMapper()
    }

    @Provides
    @FeatureScope
    fun providePhotographerDataToDomainMapper() : PhotographerDataToDomainMapper<CachePhotographer.Base> {
        return BasePhotographerDataToCacheMapper()
    }

    @Provides
    @FeatureScope
    fun provideFavouriteCacheDataSource(
        dao: PhotographerDao,
        mapperCache: PhotographerDataToDomainMapper<CachePhotographer.Base>,
        mapperData: Abstract.ToPhotographerMapper<PhotographerData>
    ): CacheFavouriteDataSource {
        return CacheFavouriteDataSource.Base(dao, mapperCache, mapperData)
    }


    @Provides
    @FeatureScope
    fun provideTokenToSharedPreferences(resourceProvider: ResourceProvider): TokenToSharedPreferences {
        return TokenToSharedPreferences.Base(resourceProvider, Reader())
    }

}