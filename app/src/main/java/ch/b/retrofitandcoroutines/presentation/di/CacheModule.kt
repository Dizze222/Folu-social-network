package ch.b.retrofitandcoroutines.presentation.di

import android.content.Context
import androidx.room.Room
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDao
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerDataBase
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.Cache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    private companion object {
        private const val DATABASE_NAME = "photographer_db"
    }

    @Provides
    @Singleton
    fun providePhotographerDatabase(@ApplicationContext context: Context): PhotographerDataBase {
        return Room.databaseBuilder(
            context,
            PhotographerDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePhotographerDao(photographerDatabase: PhotographerDataBase) : PhotographerDao{
        return photographerDatabase.photographerDao()
    }

    @Provides
    @Singleton
    fun provideCacheDataSource(
        dao: PhotographerDao,
        mapper: Abstract.ToCachePhotographerMapper<CachePhotographer>
    ) : PhotographerListCacheDataSource{
        return PhotographerListCacheDataSource.Base(dao, mapper)
    }

    @Provides
    @Singleton
    fun provideToCachePhotographerMapper() : Abstract.ToCachePhotographerMapper<CachePhotographer>{
        return Cache()
    }

}