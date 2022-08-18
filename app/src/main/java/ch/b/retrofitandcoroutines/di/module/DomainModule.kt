package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerRepository
import ch.b.retrofitandcoroutines.all_posts.data.mappers.BaseToPhotographerDomainMapper
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.authorization.data.AuthenticationRepository
import ch.b.retrofitandcoroutines.certain_post.data.CertainPostRepository
import ch.b.retrofitandcoroutines.registration.data.RegistrationRepository
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.favourite_post.data.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.splash.data.SplashService
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileRepository
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.domain.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.domain.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerInteractor
import ch.b.retrofitandcoroutines.authorization.domain.AuthenticationInteractor
import ch.b.retrofitandcoroutines.certain_post.domain.CertainPostInteractor
import ch.b.retrofitandcoroutines.favourite_post.domain.FavouritePostInteractor
import ch.b.retrofitandcoroutines.registration.domain.BaseRegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.domain.BaseRegistrationListDataToDomainMapper
import ch.b.retrofitandcoroutines.registration.domain.RegistrationInteractor
import ch.b.retrofitandcoroutines.splash.domain.SplashInteractor
import ch.b.retrofitandcoroutines.user_profile.domain.BaseUserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.user_profile.domain.BaseUserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileInteractor
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    @FeatureScope
    fun providePhotographerInteractor(
        repository: PhotographerRepository,
        mapper: PhotographerListDataToDomainMapper
    ): PhotographerInteractor {
        return PhotographerInteractor.Base(
            repository, mapper
        )
    }

    @Provides
    @FeatureScope
    fun provideAuthenticationInteractor(
        repository: AuthenticationRepository,
        mapper: AuthorizationListDataToDomainMapper
    ): AuthenticationInteractor {
        return AuthenticationInteractor.Base(repository, mapper)
    }


    @Provides
    @FeatureScope
    fun provideRegistrationInteractor(
        repository: RegistrationRepository,
        mapper: AuthorizationListDataToDomainMapper
    ): RegistrationInteractor {
        return RegistrationInteractor.Base(repository, mapper)
    }

    @Provides
    @FeatureScope
    fun providePhotographerListDataToDomainMapper(): PhotographerListDataToDomainMapper {
        return BasePhotographerListDataToDomainMapper(BasePhotographerDataToDomainMapper())
    }

    @Provides
    @FeatureScope
    fun provideUserProfileListDataToDomainMapper() : UserProfileListDataToDomainMapper {
        return BaseUserProfileListDataToDomainMapper(BaseUserProfileDataToDomainMapper())
    }

    @Provides
    @FeatureScope
    fun provideRegistrationListDataToDomainMapper(): AuthorizationListDataToDomainMapper {
        return BaseRegistrationListDataToDomainMapper(BaseRegistrationDataToDomainMapper())
    }


    @Provides
    @FeatureScope
    fun provideCertainPostInteractor(
        repository: CertainPostRepository,
        mapper: PhotographerListDataToDomainMapper
    ): CertainPostInteractor {
        return CertainPostInteractor.Base(repository, mapper)
    }

    @Provides
    @FeatureScope
    fun provideSplashInteractor(service: SplashService): SplashInteractor {
        return SplashInteractor.Base(service)
    }

    @Provides
    @FeatureScope
    fun providePhotographerDomainToUiMapper(): PhotographerDomainToUIMapper<PhotographerData> {
        return BaseToPhotographerDomainMapper()
    }

    @Provides
    @FeatureScope
    fun provideFavouritePostInteractor(
        dataSource: CacheFavouriteDataSource,
        mapper: PhotographerListDataToDomainMapper,
        mapperData: PhotographerDomainToUIMapper<PhotographerData>
    ): FavouritePostInteractor {
        return FavouritePostInteractor.Base(dataSource, mapper, mapperData)
    }

    @Provides
    @FeatureScope
    fun provideUserProfileInteractor(
        repository: UserProfileRepository,
        mapper: UserProfileListDataToDomainMapper
    ): UserProfileInteractor {
        return UserProfileInteractor.Base(repository,mapper)
    }


}