package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.core.FeatureScope
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
    @FeatureScope
    fun provideCommunication(): PhotographerCommunication {
        return PhotographerCommunication.Base()
    }

    @Provides
    @FeatureScope
    fun provideUserProfileCommunication() : UserProfileCommunication{
        return UserProfileCommunication.Base()
    }


    @Provides
    @FeatureScope
    fun provideRegistrationCommunication(): RegistrationCommunication {
        return RegistrationCommunication.Base()
    }

    @Provides
    @FeatureScope
    fun provideRegistrationMapper(): RegistrationListDomainToUIMapper {
        return BaseRegistrationListDomainToUIMapper(
            BaseRegistrationDomainToUIMapper()
        )
    }


    @Provides
    @FeatureScope
    fun providePhotographerListDomainToUiMapper(): PhotographerListDomainToUIMapper {
        return BasePhotographerListDomainToUIMapper(BasePhotographerDomainToUIMapper())
    }

    @Provides
    @FeatureScope
    fun provideUserProfileListDomainToUiMapper() : UserProfileListDomainToUiMapper{
        return BaseUserProfileListDomainToUiMapper(BaseUserProfileDomainToUiMapper())
    }

}
