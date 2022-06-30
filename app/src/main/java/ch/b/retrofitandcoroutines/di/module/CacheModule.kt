package ch.b.retrofitandcoroutines.di.module

import android.content.Context
import androidx.room.Room
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDao
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.BaseToCachePhotographerMapper
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.data.core.authorization.Reader
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.favourite_post.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.data.favourite_post.mappers.BasePhotographerDataToCacheMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    private companion object {
        private const val DATABASE_NAME = "photographer_db"
    }


    @Provides
    @Singleton
    fun providePhotographerDatabase(context: Context): PhotographerDataBase {
        return Room.databaseBuilder(
            context,
            PhotographerDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePhotographerDao(photographerDatabase: PhotographerDataBase): PhotographerDao {
        return photographerDatabase.photographerDao()
    }

    @Provides
    @Singleton
    fun provideCacheDataSource(
        dao: PhotographerDao,
        mapper: Abstract.ToPhotographerMapper<CachePhotographer.Base>
    ): PhotographerListCacheDataSource {
        return PhotographerListCacheDataSource.Base(dao, mapper)
    }

    @Provides
    @Singleton
    fun provideToCachePhotographerMapper(): Abstract.ToPhotographerMapper<CachePhotographer.Base> {
        return BaseToCachePhotographerMapper()
    }

    @Provides
    @Singleton
    fun providePhotographerDataToDomainMapper() : PhotographerDataToDomainMapper<CachePhotographer.Base>{
        return BasePhotographerDataToCacheMapper()
    }

    @Provides
    @Singleton
    fun provideFavouriteCacheDataSource(
        dao: PhotographerDao,
        mapperCache: PhotographerDataToDomainMapper<CachePhotographer.Base>,
        mapperData: Abstract.ToPhotographerMapper<PhotographerData>
    ): CacheFavouriteDataSource {
        return CacheFavouriteDataSource.Base(dao, mapperCache, mapperData)
    }


    @Provides
    @Singleton
    fun provideTokenToSharedPreferences(context: Context): TokenToSharedPreferences {
        return TokenToSharedPreferences.Base(context, Reader())
    }

}