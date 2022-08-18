package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerRepository
import ch.b.retrofitandcoroutines.all_posts.data.cache.PhotographerListCacheDataSource
import ch.b.retrofitandcoroutines.all_posts.data.mappers.BaseToDataPhotographerMapper
import ch.b.retrofitandcoroutines.all_posts.data.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.all_posts.data.mappers.ToPhotographerMapper
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerListCloudDataSource
import ch.b.retrofitandcoroutines.authorization.data.AuthenticationRepository
import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.certain_post.data.CertainPostRepository
import ch.b.retrofitandcoroutines.certain_post.data.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.utils.core_network.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.ToAuthorizationMapper
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationCloudDataSource
import ch.b.retrofitandcoroutines.registration.data.RegistrationRepository
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileData
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileRepository
import ch.b.retrofitandcoroutines.user_profile.data.mapper.BaseToProfileMapper
import ch.b.retrofitandcoroutines.user_profile.data.mapper.ExceptionProfileMapper
import ch.b.retrofitandcoroutines.user_profile.data.network.UserProfileCloudDataSource
import ch.b.retrofitandcoroutines.user_profile.data.network.UserProfileService
import ch.b.retrofitandcoroutines.utils.core_ui.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    @FeatureScope
    fun providePhotographerRepository(
        cloudDataSource: PhotographerListCloudDataSource,
        cacheDataSource: PhotographerListCacheDataSource,
        toRoomMapper: Abstract.ToPhotographerMapper<PhotographerData>,
        exceptionMapper: ExceptionPostsMapper
    ): PhotographerRepository {
        return PhotographerRepository.Base(
            cloudDataSource, cacheDataSource, toRoomMapper, exceptionMapper
        )
    }

    @Provides
    @FeatureScope
    fun provideExceptionPostsMapper(resourcesProvider: ResourceProvider): ExceptionPostsMapper {
        return ExceptionPostsMapper.Base(resourcesProvider)
    }

    @Provides
    @FeatureScope
    fun provideCloudMapper(): PhotographerListCloudMapper {
        return PhotographerListCloudMapper.Base(ToPhotographerMapper())
    }

    @Provides
    @FeatureScope
    fun provideExceptionMapper(resourcesProvider: ResourceProvider): ExceptionAuthMapper.Registration {
        return ExceptionAuthMapper.Registration(resourcesProvider)
    }

    @Provides
    @FeatureScope
    fun provideExceptionProfileMapper(resourcesProvider: ResourceProvider) : ExceptionProfileMapper.Base{
        return ExceptionProfileMapper.Base(resourcesProvider)
    }

    @Provides
    @FeatureScope
    fun provideUserProfileDataSource(service: UserProfileService): UserProfileCloudDataSource {
        return UserProfileCloudDataSource.Base(service)
    }


    @Provides
    @FeatureScope
    fun provideAuthExceptionMapper(resourcesProvider: ResourceProvider): ExceptionAuthMapper.Authorization {
        return ExceptionAuthMapper.Authorization(resourcesProvider)
    }


    @Provides
    @FeatureScope
    fun provideRoomMapper(): Abstract.ToPhotographerMapper<PhotographerData> {
        return BaseToDataPhotographerMapper()
    }

    @Provides
    @FeatureScope
    fun provideRegistrationCloudMapper(): AuthorizationListCloudMapper {
        return AuthorizationListCloudMapper.Base(ToAuthorizationMapper())
    }

    @Provides
    @FeatureScope
    fun provideCertainPostRepository(
        cloudDataSource: CertainPostDataSource,
        cloudMapper: PhotographerListCloudMapper,
        exceptionMapper: ExceptionPostsMapper
    ): CertainPostRepository {
        return CertainPostRepository.Base(cloudDataSource, cloudMapper, exceptionMapper)
    }


    @Provides
    @FeatureScope
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
    @FeatureScope
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

    @Provides
    @FeatureScope
    fun provideBaseToProfileMapper() : Abstract.ToProfileMapper<UserProfileData> {
        return BaseToProfileMapper()
    }


    @Provides
    @FeatureScope
    fun provideUserProfileRepository(
        dataSource: UserProfileCloudDataSource,
        mapper: Abstract.ToProfileMapper<UserProfileData>,
        exceptionMapper: ExceptionProfileMapper.Base
    ): UserProfileRepository {
        return UserProfileRepository.Base(dataSource,mapper, exceptionMapper)
    }

}