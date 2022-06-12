package ch.b.retrofitandcoroutines.di.module

import android.content.Context
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.certain_post.CertainPostInteractor
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.BasePhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.BasePhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import ch.b.retrofitandcoroutines.presentation.registration.BaseRegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.registration.BaseRegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationCommunication
import dagger.Module
import dagger.Provides



@Module
class PresentationModule {


    @Provides
    fun provideCommunication(): PhotographerCommunication {
        return PhotographerCommunication.Base()
    }

    @Provides
    fun provideRegistrationCommunication(): RegistrationCommunication {
        return RegistrationCommunication.Base()
    }

    @Provides
    fun provideRegistrationMapper(context: Context): RegistrationListDomainToUIMapper {
        return BaseRegistrationListDomainToUIMapper(
            BaseRegistrationDomainToUIMapper(), ResourceProvider.Base(context)
        )
    }


    @Provides
    fun providePhotographerListDomainToUiMapper(): PhotographerListDomainToUIMapper {
        return BasePhotographerListDomainToUIMapper(BasePhotographerDomainToUIMapper())
    }

    @Provides
    fun provideCertainPostInteractor(
        repository: CertainPostRepository,
        mapper: PhotographerListDataToDomainMapper
    ): CertainPostInteractor {
        return CertainPostInteractor.Base(repository, mapper)
    }


}