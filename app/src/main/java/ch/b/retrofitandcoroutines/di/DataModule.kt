package ch.b.retrofitandcoroutines.di

import android.content.Context
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.*
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographersCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.AuthenticationRepository
import ch.b.retrofitandcoroutines.data.authorization.net.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.ToAuthorizationMapper
import ch.b.retrofitandcoroutines.data.registration.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.data.registration.RegistrationRepository
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        toRoomMapper: Abstract.ToPhotographerMapper<PhotographerData>,
        exceptionMapper: ExceptionPostsMapper
    ): PhotographerRepository {
        return PhotographerRepository.Base(
            cloudDataSource, cacheDataSource, cloudMapper, toRoomMapper, exceptionMapper
        )
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider.Base(context)
    }

    @Provides
    @Singleton
    fun provideExceptionPostsMapper(resourcesProvider: ResourceProvider): ExceptionPostsMapper {
        return ExceptionPostsMapper.Base(resourcesProvider)
    }

    @Provides
    @Singleton
    fun provideCloudMapper(): PhotographerListCloudMapper {
        return PhotographerListCloudMapper.Base(ToPhotographerMapper())
    }

    @Provides
    @Singleton
    fun provideExceptionMapper(resourcesProvider: ResourceProvider): ExceptionAuthMapper {
        return ExceptionAuthMapper.Base(resourcesProvider)
    }


    @Provides
    @Singleton
    fun provideRoomMapper(): Abstract.ToPhotographerMapper<PhotographerData> {
        return BaseToDataPhotographerMapper()
    }

    @Provides
    @Singleton
    fun provideRegistrationCloudMapper(): AuthorizationListCloudMapper {
        return AuthorizationListCloudMapper.Base(ToAuthorizationMapper())
    }

    @Provides
    @Singleton
    fun provideCertainPostRepository(
        cloudDataSource: CertainPostDataSource,
        cloudMapper: PhotographerListCloudMapper,
        exceptionMapper: ExceptionPostsMapper
    ): CertainPostRepository {
        return CertainPostRepository.Base(cloudDataSource, cloudMapper, exceptionMapper)
    }


    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        registerDataSource: RegistrationCloudDataSource,
        dataSource: AuthenticationCloudDataSource,
        cloudMapper: AuthorizationListCloudMapper,
        exceptionMapper: ExceptionAuthMapper,
        tokenToSharedPreferences: TokenToSharedPreferences

    ): AuthenticationRepository {
        return AuthenticationRepository(
            registerDataSource,
            cloudMapper,
            exceptionMapper,
            tokenToSharedPreferences,
            dataSource
        )
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(
        registerDataSource: RegistrationCloudDataSource,
        dataSource: AuthenticationCloudDataSource,
        cloudMapper: AuthorizationListCloudMapper,
        exceptionMapper: ExceptionAuthMapper,
        tokenToSharedPreferences: TokenToSharedPreferences
    ): RegistrationRepository {
        return RegistrationRepository(
            registerDataSource,
            cloudMapper,
            exceptionMapper,
            tokenToSharedPreferences,
            dataSource
        )
    }

}