package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.data.all_posts.mappers.*
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudDataSource
import ch.b.retrofitandcoroutines.data.authorization.AuthenticationRepository
import ch.b.retrofitandcoroutines.data.authorization.net.authorization.AuthenticationCloudDataSource
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
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    @Singleton
    fun providePhotographerRepository(
        cloudDataSource: PhotographerListCloudDataSource,
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
    fun provideExceptionMapper(resourcesProvider: ResourceProvider): ExceptionAuthMapper.Registration {
        return ExceptionAuthMapper.Registration(resourcesProvider)
    }

    @Provides
    @Singleton
    fun provideAuthExceptionMapper(resourcesProvider: ResourceProvider): ExceptionAuthMapper.Authorization {
        return ExceptionAuthMapper.Authorization(resourcesProvider)
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
        exceptionAuthMapper: ExceptionAuthMapper.Authorization,
        exceptionRegisterMapper: ExceptionAuthMapper.Registration,
        tokenToSharedPreferences: TokenToSharedPreferences

    ): AuthenticationRepository {
        return AuthenticationRepository(
            registerDataSource,
            cloudMapper,
            exceptionRegisterMapper,
            exceptionAuthMapper,
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
        exceptionAuthMapper: ExceptionAuthMapper.Authorization,
        exceptionRegisterMapper: ExceptionAuthMapper.Registration,
        tokenToSharedPreferences: TokenToSharedPreferences
    ): RegistrationRepository {
        return RegistrationRepository(
            registerDataSource,
            cloudMapper,
            exceptionRegisterMapper,
            exceptionAuthMapper,
            tokenToSharedPreferences,
            dataSource
        )
    }

}