package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.registration.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.core.BasePhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.core.BasePhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.presentation.registration.BaseRegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.registration.BaseRegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationCommunication
import ch.b.retrofitandcoroutines.presentation.user_profile.BaseUserProfileDomainToUiMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.BaseUserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.core.UserProfileCommunication
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {


    @Provides
    fun provideCommunication(): PhotographerCommunication {
        return PhotographerCommunication.Base()
    }

    @Provides
    fun provideUserProfileCommunication() : UserProfileCommunication{
        return UserProfileCommunication.Base()
    }


    @Provides
    fun provideRegistrationCommunication(): RegistrationCommunication {
        return RegistrationCommunication.Base()
    }

    @Provides
    fun provideRegistrationMapper(): RegistrationListDomainToUIMapper {
        return BaseRegistrationListDomainToUIMapper(
            BaseRegistrationDomainToUIMapper()
        )
    }


    @Provides
    fun providePhotographerListDomainToUiMapper(): PhotographerListDomainToUIMapper {
        return BasePhotographerListDomainToUIMapper(BasePhotographerDomainToUIMapper())
    }

    @Provides
    fun provideUserProfileListDomainToUiMapper() : UserProfileListDomainToUiMapper{
        return BaseUserProfileListDomainToUiMapper(BaseUserProfileDomainToUiMapper())
    }

}
