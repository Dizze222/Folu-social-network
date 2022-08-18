package ch.b.retrofitandcoroutines.di.module

import ch.b.retrofitandcoroutines.utils.core.FeatureScope
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.domain.RegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.core.BasePhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.core.BasePhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.core.PhotographerCommunication
import ch.b.retrofitandcoroutines.registration.presentation.BaseRegistrationDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.presentation.BaseRegistrationListDomainToUIMapper
import ch.b.retrofitandcoroutines.registration.presentation.RegistrationCommunication
import ch.b.retrofitandcoroutines.user_profile.presentation.BaseUserProfileDomainToUiMapper
import ch.b.retrofitandcoroutines.user_profile.presentation.BaseUserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.user_profile.presentation.core.UserProfileCommunication
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
    fun provideUserProfileCommunication() : UserProfileCommunication {
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
    fun provideUserProfileListDomainToUiMapper() : UserProfileListDomainToUiMapper {
        return BaseUserProfileListDomainToUiMapper(BaseUserProfileDomainToUiMapper())
    }

}
