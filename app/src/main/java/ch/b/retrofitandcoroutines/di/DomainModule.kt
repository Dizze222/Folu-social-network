package ch.b.retrofitandcoroutines.di

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.authorization.AuthenticationRepository
import ch.b.retrofitandcoroutines.data.registration.RegistrationRepository
import ch.b.retrofitandcoroutines.data.registration.mappers.RegistrationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.BasePhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerInteractor
import ch.b.retrofitandcoroutines.domain.authorization.AuthenticationInteractor
import ch.b.retrofitandcoroutines.domain.registration.BaseRegistrationDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.BaseRegistrationListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
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
        mapper: RegistrationListDataToDomainMapper
    ): AuthenticationInteractor {
        return AuthenticationInteractor.Base(repository,mapper)
    }


    @Provides
    @Singleton
    fun provideRegistrationInteractor(
        repository: RegistrationRepository,
        mapper: RegistrationListDataToDomainMapper
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
    fun provideRegistrationListDataToDomainMapper(): RegistrationListDataToDomainMapper {
        return BaseRegistrationListDataToDomainMapper(BaseRegistrationDataToDomainMapper())
    }
}