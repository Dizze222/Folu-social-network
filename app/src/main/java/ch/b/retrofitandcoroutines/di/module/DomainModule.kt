package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.mappers.BaseToPhotographerDomainMapper
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.authorization.AuthenticationRepository
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.data.registration.RegistrationRepository
import ch.b.retrofitandcoroutines.data.core.authorization.mappers.AuthorizationListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.favourite_post.cache.CacheFavouriteDataSource
import ch.b.retrofitandcoroutines.data.splash.SplashService
import ch.b.retrofitandcoroutines.data.user_profile.UserProfileRepository
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.authorization.AuthenticationInteractor
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.domain.favourite_post.FavouritePostInteractor
import ch.b.retrofitandcoroutines.domain.registration.BaseRegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.BaseRegistrationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationInteractor
import ch.b.retrofitandcoroutines.domain.splash.SplashInteractor
import ch.b.retrofitandcoroutines.domain.user_profile.BaseUserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.user_profile.BaseUserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePhotographerInteractor(
        repository: PhotographerRepository,
        mapper: PhotographerListDataToDomainMapper
    ): PhotographerInteractor {
        return PhotographerInteractor.Base(
            repository, mapper
        )
    }

    @Provides
    @Singleton
    fun provideAuthenticationInteractor(
        repository: AuthenticationRepository,
        mapper: AuthorizationListDataToDomainMapper
    ): AuthenticationInteractor {
        return AuthenticationInteractor.Base(repository, mapper)
    }


    @Provides
    @Singleton
    fun provideRegistrationInteractor(
        repository: RegistrationRepository,
        mapper: AuthorizationListDataToDomainMapper
    ): RegistrationInteractor {
        return RegistrationInteractor.Base(repository, mapper)
    }

    @Provides
    @Singleton
    fun providePhotographerListDataToDomainMapper(): PhotographerListDataToDomainMapper {
        return BasePhotographerListDataToDomainMapper(BasePhotographerDataToDomainMapper())
    }

    @Provides
    @Singleton
    fun provideUserProfileListDataToDomainMapper() : UserProfileListDataToDomainMapper{
        return BaseUserProfileListDataToDomainMapper(BaseUserProfileDataToDomainMapper())
    }

    @Provides
    @Singleton
    fun provideRegistrationListDataToDomainMapper(): AuthorizationListDataToDomainMapper {
        return BaseRegistrationListDataToDomainMapper(BaseRegistrationDataToDomainMapper())
    }


    @Provides
    @Singleton
    fun provideCertainPostInteractor(
        repository: CertainPostRepository,
        mapper: PhotographerListDataToDomainMapper
    ): CertainPostInteractor {
        return CertainPostInteractor.Base(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideSplashInteractor(service: SplashService): SplashInteractor {
        return SplashInteractor.Base(service)
    }

    @Provides
    @Singleton
    fun providePhotographerDomainToUiMapper(): PhotographerDomainToUIMapper<PhotographerData> {
        return BaseToPhotographerDomainMapper()
    }

    @Provides
    @Singleton
    fun provideFavouritePostInteractor(
        dataSource: CacheFavouriteDataSource,
        mapper: PhotographerListDataToDomainMapper,
        mapperData: PhotographerDomainToUIMapper<PhotographerData>
    ): FavouritePostInteractor {
        return FavouritePostInteractor.Base(dataSource, mapper, mapperData)
    }

    @Provides
    @Singleton
    fun provideUserProfileInteractor(
        repository: UserProfileRepository,
        mapper: UserProfileListDataToDomainMapper
    ): UserProfileInteractor {
        return UserProfileInteractor.Base(repository,mapper)
    }


}