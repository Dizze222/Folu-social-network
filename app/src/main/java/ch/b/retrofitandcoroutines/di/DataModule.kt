package ch.b.retrofitandcoroutines.di

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.*
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListCloudMapper
import ch.b.retrofitandcoroutines.data.registration.mappers.ToRegistrationMapper
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.data.registration.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePhotographerRepository(
        cloudDataSource: PhotographersCloudDataSource,
        cacheDataSource: PhotographerListCacheDataSource,
        cloudMapper: PhotographerListCloudMapper,
        toRoomMapper: Abstract.ToPhotographerMapper<PhotographerData>
    ): PhotographerRepository {
        return PhotographerRepository.Base(
            cloudDataSource, cacheDataSource, cloudMapper, toRoomMapper
        )
    }

    @Provides
    @Singleton
    fun provideCloudMapper(): PhotographerListCloudMapper {
        return PhotographerListCloudMapper.Base(ToPhotographerMapper())
    }

    @Provides
    @Singleton
    fun provideRoomMapper(): Abstract.ToPhotographerMapper<PhotographerData> {
        return BaseToDataPhotographerMapper()
    }

    @Provides
    @Singleton
    fun provideRegistrationCloudMapper() : RegistrationListCloudMapper{
        return RegistrationListCloudMapper.Base(ToRegistrationMapper())
    }
    @Provides
    @Singleton
    fun provideCertainPostRepository(
        cloudDataSource: CertainPostDataSource,
        cloudMapper: PhotographerListCloudMapper
    ): CertainPostRepository {
        return CertainPostRepository.Base(cloudDataSource, cloudMapper)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(
        cloudDataSource: RegistrationCloudDataSource,
        cloudMapper: RegistrationListCloudMapper
    ) : RegistrationRepository {
        return RegistrationRepository.Base(cloudDataSource,cloudMapper)
    }

}